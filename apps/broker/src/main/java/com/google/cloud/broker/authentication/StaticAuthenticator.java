package com.google.cloud.broker.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticAuthenticator implements Authenticator {
    private static final Logger logger = LoggerFactory.getLogger(StaticAuthenticator.class);
    private String user;
    public StaticAuthenticator(String user){
        logger.debug("Initialized with DEMO_IMPERSONATOR " + user);
        this.user = user;
    }

    @Override
    public String authenticateUser() {
        logger.debug("Authenticated as DEMO_IMPERSONATOR " + user);
        return user;
    }
}
