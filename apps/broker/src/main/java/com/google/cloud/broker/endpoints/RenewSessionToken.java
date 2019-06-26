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

package com.google.cloud.broker.endpoints;

import com.google.cloud.broker.authentication.Authenticator;
import com.google.cloud.broker.sessions.SessionTokenUtils;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.google.cloud.broker.authentication.SpnegoAuthenticator;
import com.google.cloud.broker.database.models.Model;
import com.google.cloud.broker.protobuf.RenewSessionTokenRequest;
import com.google.cloud.broker.protobuf.RenewSessionTokenResponse;
import com.google.cloud.broker.sessions.Session;
import com.google.cloud.broker.logging.LoggingUtils;
import com.google.cloud.broker.validation.Validation;


public class RenewSessionToken {
    private static final Logger logger = LoggerFactory.getLogger(RenewSessionToken.class);

    private static final Authenticator authenticator = SpnegoAuthenticator.getInstance();

    public static void run(RenewSessionTokenRequest request, StreamObserver<RenewSessionTokenResponse> responseObserver) {

        String authenticatedUser = authenticator.authenticateUser();

        Validation.validateNotEmpty("session_token", request.getSessionToken());

        // Retrieve the session details from the database
        Session session = SessionTokenUtils.getSessionFromRawToken(request.getSessionToken());

        // Verify that the caller is the authorized renewer for the toke
        String authorizedRenewer = (String) session.getValue("renewer");
        if (!authorizedRenewer.equals(authenticatedUser)) {
            logger.debug("authorizedRenewer=" + authorizedRenewer + " but authenticatedUser=" + authenticatedUser);
            throw Status.PERMISSION_DENIED.withDescription(String.format("Unauthorized renewer: %s", authenticatedUser)).asRuntimeException();
        }

        // Extend session's lifetime
        session.extendLifetime();
        Model.save(session);

        String owner = (String) session.getValue("owner");
        String id = (String) session.getValue("id");
        long expiresAt = (long) session.getValue("expires_at");

        logger.info(authorizedRenewer + " renewed " + id + " for " + owner);

        responseObserver.onNext(RenewSessionTokenResponse.newBuilder()
                .setExpiresAt(expiresAt)
                .build());
        responseObserver.onCompleted();
    }
}
