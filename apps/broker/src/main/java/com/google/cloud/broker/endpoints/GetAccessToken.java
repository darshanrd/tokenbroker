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

import com.google.cloud.broker.accesstokens.AccessTokenCacheFetcher;
import com.google.cloud.broker.authentication.Authenticator;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.cloud.broker.validation.Validation;
import com.google.cloud.broker.authentication.SessionAuthenticator;
import com.google.cloud.broker.sessions.Session;
import com.google.cloud.broker.accesstokens.AccessToken;
import com.google.cloud.broker.authentication.SpnegoAuthenticator;
import com.google.cloud.broker.protobuf.GetAccessTokenRequest;
import com.google.cloud.broker.protobuf.GetAccessTokenResponse;


public class GetAccessToken {
    private static final Logger logger = LoggerFactory.getLogger(GetAccessToken.class);
    private static final SessionAuthenticator sessionAuthenticator = new SessionAuthenticator();
    private static final Authenticator spnegoAuthenticator = SpnegoAuthenticator.getInstance();

    public static void run(GetAccessTokenRequest request, StreamObserver<GetAccessTokenResponse> responseObserver) {
        // First try to authenticate the session, if any.
        Session session = sessionAuthenticator.authenticateSession();
        String owner = request.getOwner();
        String scope = request.getScope();

        if (session == null) {
            // No session token was provided. The client is using direct authentication.
            String authenticatedUser = spnegoAuthenticator.authenticateUser();

            Validation.validateImpersonator(authenticatedUser, owner);
            logger.debug(authenticatedUser + " requests Access Token for " + owner);
        } else {
            // A session token was provided. The client is using delegated authentication.
            Validation.validateNotEmpty("owner", owner);
            Validation.validateNotEmpty("scope", scope);
            Validation.validateScope(scope);
            String requestTarget = request.getTarget();
            String sessionTarget = (String) session.getValue("target");
            String sessionOwner = (String) session.getValue("owner");
            String sessionOwnerUsername = sessionOwner.split("@")[0];

            if (!requestTarget.equals(sessionTarget)) {
                throw Status.PERMISSION_DENIED
                    .withDescription("Target mismatch")
                    .asRuntimeException();
            } else if (!scope.equals(session.getValue("scope"))) {
                throw Status.PERMISSION_DENIED
                    .withDescription("Scope mismatch")
                    .asRuntimeException();
            } else if (!owner.equals(sessionOwner) && !owner.equals(sessionOwnerUsername)) {
                throw Status.PERMISSION_DENIED
                    .withDescription("Owner mismatch")
                    .asRuntimeException();
            }
            logger.debug("Access Token for " + owner + " authorized by delegation");
        }

        AccessToken accessToken = (AccessToken) new AccessTokenCacheFetcher(owner, scope).fetch();

        responseObserver.onNext(GetAccessTokenResponse.newBuilder()
                .setAccessToken(accessToken.getValue())
                .setExpiresAt(accessToken.getExpiresAt())
                .build());
        responseObserver.onCompleted();
    }
}
