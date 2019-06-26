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

import java.util.HashMap;

import com.google.cloud.broker.authentication.Authenticator;
import com.google.cloud.broker.logging.LoggingUtils;
import com.google.protobuf.TextFormat;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.google.cloud.broker.database.models.Model;
import com.google.cloud.broker.sessions.Session;
import com.google.cloud.broker.sessions.SessionTokenUtils;
import com.google.cloud.broker.validation.Validation;
import com.google.cloud.broker.authentication.SpnegoAuthenticator;
import com.google.cloud.broker.protobuf.GetSessionTokenRequest;
import com.google.cloud.broker.protobuf.GetSessionTokenResponse;


public class GetSessionToken {
    private static final Logger logger = LoggerFactory.getLogger(GetSessionToken.class);
    private static final Authenticator authenticator = SpnegoAuthenticator.getInstance();

    public static void run(GetSessionTokenRequest request, StreamObserver<GetSessionTokenResponse> responseObserver) {

        String authenticatedUser = authenticator.authenticateUser();

        logger.debug(TextFormat.printToString(request));
        Validation.validateNotEmpty("owner", request.getOwner());
        Validation.validateNotEmpty("scope", request.getScope());
        Validation.validateImpersonator(authenticatedUser, request.getOwner());

        // Create session
        HashMap<String, Object> values = new HashMap<String, Object>();
        values.put("owner", request.getOwner());
        values.put("renewer", request.getRenewer());
        values.put("target", request.getTarget());
        values.put("scope", request.getScope());
        Session session = new Session(values);
        Model.save(session);

        // Generate session token
        String sessionToken = SessionTokenUtils.marshallSessionToken(session);

        String renewer = request.getRenewer();
        String owner = request.getOwner();
        String id = (String) session.getValue("id");
        logger.info(renewer + " obtained " + id + " for " + owner);

        responseObserver.onNext(GetSessionTokenResponse.newBuilder()
                .setSessionToken(sessionToken)
                .build());
        responseObserver.onCompleted();
    }
}
