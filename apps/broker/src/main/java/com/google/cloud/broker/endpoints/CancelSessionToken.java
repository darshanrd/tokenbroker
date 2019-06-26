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

import com.google.cloud.broker.authentication.SpnegoAuthenticator;
import com.google.cloud.broker.database.models.Model;
import com.google.cloud.broker.protobuf.CancelSessionTokenRequest;
import com.google.cloud.broker.protobuf.CancelSessionTokenResponse;
import com.google.cloud.broker.sessions.Session;
import com.google.cloud.broker.validation.Validation;


public class CancelSessionToken {
    private static final Logger logger = LoggerFactory.getLogger(CancelSessionToken.class);
    private static final Authenticator authenticator = SpnegoAuthenticator.getInstance();
    private static final CancelSessionTokenResponse response = CancelSessionTokenResponse.getDefaultInstance();

    public static void run(CancelSessionTokenRequest request, StreamObserver<CancelSessionTokenResponse> responseObserver) {

        String authenticatedUser = authenticator.authenticateUser();

        Validation.validateNotEmpty("session_token", request.getSessionToken());

        // Retrieve the session details from the database
        Session session = SessionTokenUtils.getSessionFromRawToken(request.getSessionToken());

        // Verify that the caller is the authorized renewer for the token
        String authorizedRenewer = (String) session.getValue("renewer");
        String sessionOwner = (String) session.getValue("owner");
        if (!authorizedRenewer.equals(authenticatedUser)) {
            logger.debug("Session renewer is " + authorizedRenewer + " but user is authenticated as " + authenticatedUser);
            throw Status.PERMISSION_DENIED.asRuntimeException();
        }

        // Cancel the token
        Model.delete(session);

        logger.info(authenticatedUser + " canceled delegation for " + sessionOwner);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
