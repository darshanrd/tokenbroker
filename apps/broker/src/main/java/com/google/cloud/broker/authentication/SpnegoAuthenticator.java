// Copyright 2019 Google LLC
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.cloud.broker.authentication;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Base64;
import java.security.PrivilegedAction;
import javax.security.auth.Subject;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.auth.login.Configuration;

import com.google.cloud.broker.settings.AppSettings;
import io.grpc.Status;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.Oid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SpnegoAuthenticator implements Authenticator {
    private static final Logger logger = LoggerFactory.getLogger(SpnegoAuthenticator.class);

    private static Authenticator instance = null;

    private ArrayList<Subject> logins = new ArrayList<Subject>();

    private static AppSettings settings = AppSettings.getInstance();


    private SpnegoAuthenticator() {
        // Load and validate keytabs
        File keytabsPath = new File(settings.getProperty("KEYTABS_PATH"));
        File[] keytabFiles = keytabsPath.listFiles();
        for (File keytabFile : keytabFiles) {
            if (keytabFile.isFile()) {
                sun.security.krb5.internal.ktab.KeyTab keytab = sun.security.krb5.internal.ktab.KeyTab.getInstance(keytabFile);
                sun.security.krb5.internal.ktab.KeyTabEntry[] entries = keytab.getEntries();
                if (!keytab.isValid() || entries.length < 1) {
                    throw new RuntimeException(String.format("Invalid keytab: %s", keytabFile.getPath()));
                }

                String serviceName = settings.getProperty("BROKER_SERVICE_NAME");
                String hostname = settings.getProperty("BROKER_SERVICE_HOSTNAME");
                String realm = null;

                // Perform further validation of entries in the keytab
                for (sun.security.krb5.internal.ktab.KeyTabEntry entry : entries) {
                    String[] nameStrings = entry.getService().getNameStrings();

                    // Ensure that the entries' service name and hostname match the whitelisted values
                    if (!nameStrings[0].equals(serviceName) || !nameStrings[1].equals(hostname)) {
                        throw new RuntimeException(String.format("Invalid service name or hostname in keytab: %s", keytabFile.getPath()));
                    }

                    // Ensure that all entries share the same realm
                    String entryRealm = entry.getService().getRealmAsString();
                    if (realm == null) {
                        realm = entryRealm;
                    } else if (!entryRealm.equals(realm)) {
                        throw new RuntimeException(String.format("Keytab `%s` contains multiple realms: %s, %s", keytabFile.getPath(), realm, entryRealm));
                    }
                }

                String principal = serviceName + "/" + hostname + "@" + realm;
                Subject subject = login(principal, keytabFile);
                logins.add(subject);
            }
        }
    }


    private Subject login(String principal, File keytabFile) {
        try {
            LoginContext loginContext = new LoginContext(
                    "", new Subject(), null, getConfiguration(principal, keytabFile));
            loginContext.login();
            Subject subject = loginContext.getSubject();
            return subject;
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }


    private static Configuration getConfiguration(String principal, File keytabFile) { ;
        return new Configuration() {
            @Override
            public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
            Map<String, String> options = new HashMap<String, String>();
            options.put("principal", principal);
            options.put("keyTab", keytabFile.getPath());
            options.put("doNotPrompt", "true");
            options.put("useKeyTab", "true");
            options.put("storeKey", "true");
            options.put("isInitiator", "false");
            return new AppConfigurationEntry[] {
                new AppConfigurationEntry(
                    "com.sun.security.auth.module.Krb5LoginModule",
                    AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options)
            };
            }
        };
    }


    public static Authenticator getInstance() {
        if (instance == null) {
            if (settings.getProperty("AUTHENTICATOR").equals("com.google.cloud.broker.authentication.StaticAuthenticator")) {
                String user = settings.getProperty("DEMO_IMPERSONATOR");
                instance = new StaticAuthenticator(user);
            } else {
                instance = new SpnegoAuthenticator();
            }
        }
        return instance;
    }

    private static Oid getOid(String oid) {
        try {
            return new Oid(oid);
        } catch (GSSException e){
            throw new RuntimeException(e);
        }
    }
    private static final String SPNEGO_OID_STR = "1.3.6.1.5.5.2";
    private static final Oid SPNEGO_OID = getOid(SPNEGO_OID_STR);

    @Override
    public String authenticateUser() {
        String authorizationHeader = AuthorizationHeaderServerInterceptor.AUTHORIZATION_CONTEXT_KEY.get();
        if (authorizationHeader == null) {
            throw Status.UNAUTHENTICATED
                    .withDescription("Negotiate header not set")
                    .asRuntimeException();
        } else if (!authorizationHeader.startsWith("Negotiate ")) {
            throw Status.UNAUTHENTICATED
                    .withDescription("Invalid Authorization header")
                    .asRuntimeException();
        }
        String spnegoToken = authorizationHeader.split("\\s")[1];

        String authenticatedUser = null;

        for (Subject login : logins) {
            logger.debug("Authenticating SPNEGO token as " + login.getPrincipals().iterator().next().getName());
            authenticatedUser = Subject.doAs(login, new PrivilegedAction<String>() {
                public String run() {
                    try {
                        GSSManager manager = GSSManager.getInstance();

                        GSSCredential serverCredential = manager.createCredential(null,
                            GSSCredential.DEFAULT_LIFETIME,
                            SPNEGO_OID,
                            GSSCredential.ACCEPT_ONLY);
                        GSSContext context = manager.createContext(serverCredential);
                        byte[] tokenBytes = Base64.getDecoder().decode(spnegoToken.getBytes());
                        context.acceptSecContext(tokenBytes, 0, tokenBytes.length);

                        String authUser = context.getSrcName().toString();
                        logger.debug("Authenticated " + authUser);
                        return authUser;
                    } catch (GSSException e) {
                        return null;
                    }
                }
            });
            if (authenticatedUser != null) {
                return authenticatedUser;
            }
        }

        throw Status.UNAUTHENTICATED.asRuntimeException();
    }

}