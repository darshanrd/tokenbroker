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

package com.google.cloud.broker.grpc;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.InetSocketAddress;
import java.nio.file.Paths;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.qos.logback.core.ConsoleAppender;
import com.google.cloud.broker.logging.LoggerUtils;
import com.google.cloud.broker.logging.LoggingUtils;
import com.google.common.base.Preconditions;
import io.grpc.Server;
import io.grpc.ServerInterceptors;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslProvider;
import io.grpc.stub.StreamObserver;
import org.slf4j.LoggerFactory;

import com.google.cloud.broker.authentication.AuthorizationHeaderServerInterceptor;
import com.google.cloud.broker.endpoints.GetAccessToken;
import com.google.cloud.broker.endpoints.GetSessionToken;
import com.google.cloud.broker.endpoints.RenewSessionToken;
import com.google.cloud.broker.endpoints.CancelSessionToken;
import com.google.cloud.broker.protobuf.*;
import com.google.cloud.broker.settings.AppSettings;


public class BrokerServer {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BrokerServer.class);

    private Server server;

    private final String host;
    private final int port;
    private final String certChainFilePath;
    private final String privateKeyFilePath;

    public BrokerServer() {
        AppSettings settings = AppSettings.getInstance();
        this.host = settings.getProperty("SERVER_HOST");
        this.port = Integer.parseInt(settings.getProperty("SERVER_PORT"));
        this.certChainFilePath = settings.getProperty("TLS_CRT_PATH");
        this.privateKeyFilePath = settings.getProperty("TLS_KEY_PATH");
    }

    private SslContextBuilder getSslContextBuilder() {
        SslContextBuilder sslClientContextBuilder = SslContextBuilder.forServer(
            new File(certChainFilePath),
            new File(privateKeyFilePath));
        return GrpcSslContexts.configure(
            sslClientContextBuilder,
            SslProvider.OPENSSL);
    }

    private void start() throws IOException {
        NettyServerBuilder builder = NettyServerBuilder.forAddress(new InetSocketAddress(host, port))
                .addService(ServerInterceptors.intercept(new BrokerImpl(), new AuthorizationHeaderServerInterceptor(), new ClientAddressServerInterceptor()));
        if (Paths.get(certChainFilePath).toFile().exists() && Paths.get(privateKeyFilePath).toFile().exists()) {
            builder.sslContext(getSslContextBuilder().build());
        }
        server = builder.build().start();
        logger.info("Server listening on " + port + "...");
        Runtime.getRuntime().addShutdownHook(new ShutDownHookThread(server));
    }

    private static class ShutDownHookThread extends Thread{
        private Server server;
        public ShutDownHookThread(Server server){
            Preconditions.checkNotNull(server);
            this.server = server;
        }
        @Override
        public void run() {
            if (!server.isShutdown()) {
                server.shutdown();
            }
        }

    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }


    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    static class BrokerImpl extends BrokerGrpc.BrokerImplBase {

        @Override
        public void getAccessToken(GetAccessTokenRequest request, StreamObserver<GetAccessTokenResponse> responseObserver) {
            try {
                GetAccessToken.run(request, responseObserver);
            } catch (StatusRuntimeException e) {
                logger.error(LoggingUtils.logError(e, GetAccessToken.class.getSimpleName()), e);
                responseObserver.onError(e);
            }
        }

        @Override
        public void getSessionToken(GetSessionTokenRequest request, StreamObserver<GetSessionTokenResponse> responseObserver) {
            try {
                GetSessionToken.run(request, responseObserver);
            } catch (StatusRuntimeException e) {
                logger.error(LoggingUtils.logError(e, GetSessionToken.class.getSimpleName()), e);
                responseObserver.onError(e);
            }
        }

        @Override
        public void renewSessionToken(RenewSessionTokenRequest request, StreamObserver<RenewSessionTokenResponse> responseObserver) {
            try {
                RenewSessionToken.run(request, responseObserver);
            } catch (StatusRuntimeException e) {
                logger.error(LoggingUtils.logError(e, RenewSessionToken.class.getSimpleName()),e);
                responseObserver.onError(e);
            }
        }

        @Override
        public void cancelSessionToken(CancelSessionTokenRequest request, StreamObserver<CancelSessionTokenResponse> responseObserver) {
            try {
                CancelSessionToken.run(request, responseObserver);
            } catch (StatusRuntimeException e) {
                logger.error(LoggingUtils.logError(e, CancelSessionToken.class.getSimpleName()), e);
                responseObserver.onError(e);
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LoggerUtils.configureLogging();
        final BrokerServer server = new BrokerServer();
        server.start();
        server.blockUntilShutdown();
    }
}