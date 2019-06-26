package com.google.cloud.broker.accesstokens.providers;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.broker.accesstokens.AccessToken;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/** Looks for a JSON credential named "${owner}.json"
 *
 */
public class LocalRefreshTokenProvider extends AbstractProvider {
    /** View and manage your data across Google Cloud Platform services. */
    public static final String CLOUD_PLATFORM = "https://www.googleapis.com/auth/cloud-platform";

    /** View your data across Google Cloud Platform services. */
    public static final String CLOUD_PLATFORM_READ_ONLY = "https://www.googleapis.com/auth/cloud-platform.read-only";

    /** Manage your data and permissions in Google Cloud Storage. */
    public static final String DEVSTORAGE_FULL_CONTROL = "https://www.googleapis.com/auth/devstorage.full_control";

    /** View your data in Google Cloud Storage. */
    public static final String DEVSTORAGE_READ_ONLY = "https://www.googleapis.com/auth/devstorage.read_only";

    /** Manage your data in Google Cloud Storage. */
    public static final String DEVSTORAGE_READ_WRITE = "https://www.googleapis.com/auth/devstorage.read_write";

    private static final Logger logger = LoggerFactory.getLogger(LocalRefreshTokenProvider.class);

    @Override
    public AccessToken getAccessToken(String owner, String scope) {
        logger.debug("Access Token for " + owner + " requested with scope " + scope);
        String filename = owner.replaceAll("@.*","") + ".json";
        try {
            GoogleCredentials creds = GoogleCredentials.fromStream(new ByteArrayInputStream(Files.readAllBytes(Paths.get(filename))));
            com.google.auth.oauth2.AccessToken token = creds
                    .createScoped(ImmutableList.of(DEVSTORAGE_FULL_CONTROL))
                    .refreshAccessToken();
            return new AccessToken(token.getTokenValue(), token.getExpirationTime().getTime());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getGoogleIdentify(String owner) {
        return owner;
    }
}
