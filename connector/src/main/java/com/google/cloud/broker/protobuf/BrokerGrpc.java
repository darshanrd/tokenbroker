package com.google.cloud.broker.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.19.0)",
    comments = "Source: broker.proto")
public final class BrokerGrpc {

  private BrokerGrpc() {}

  public static final String SERVICE_NAME = "com.google.cloud.broker.protobuf.Broker";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.GetSessionTokenRequest,
      com.google.cloud.broker.protobuf.GetSessionTokenResponse> getGetSessionTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSessionToken",
      requestType = com.google.cloud.broker.protobuf.GetSessionTokenRequest.class,
      responseType = com.google.cloud.broker.protobuf.GetSessionTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.GetSessionTokenRequest,
      com.google.cloud.broker.protobuf.GetSessionTokenResponse> getGetSessionTokenMethod() {
    io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.GetSessionTokenRequest, com.google.cloud.broker.protobuf.GetSessionTokenResponse> getGetSessionTokenMethod;
    if ((getGetSessionTokenMethod = BrokerGrpc.getGetSessionTokenMethod) == null) {
      synchronized (BrokerGrpc.class) {
        if ((getGetSessionTokenMethod = BrokerGrpc.getGetSessionTokenMethod) == null) {
          BrokerGrpc.getGetSessionTokenMethod = getGetSessionTokenMethod = 
              io.grpc.MethodDescriptor.<com.google.cloud.broker.protobuf.GetSessionTokenRequest, com.google.cloud.broker.protobuf.GetSessionTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.google.cloud.broker.protobuf.Broker", "GetSessionToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.cloud.broker.protobuf.GetSessionTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.cloud.broker.protobuf.GetSessionTokenResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BrokerMethodDescriptorSupplier("GetSessionToken"))
                  .build();
          }
        }
     }
     return getGetSessionTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.RenewSessionTokenRequest,
      com.google.cloud.broker.protobuf.RenewSessionTokenResponse> getRenewSessionTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RenewSessionToken",
      requestType = com.google.cloud.broker.protobuf.RenewSessionTokenRequest.class,
      responseType = com.google.cloud.broker.protobuf.RenewSessionTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.RenewSessionTokenRequest,
      com.google.cloud.broker.protobuf.RenewSessionTokenResponse> getRenewSessionTokenMethod() {
    io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.RenewSessionTokenRequest, com.google.cloud.broker.protobuf.RenewSessionTokenResponse> getRenewSessionTokenMethod;
    if ((getRenewSessionTokenMethod = BrokerGrpc.getRenewSessionTokenMethod) == null) {
      synchronized (BrokerGrpc.class) {
        if ((getRenewSessionTokenMethod = BrokerGrpc.getRenewSessionTokenMethod) == null) {
          BrokerGrpc.getRenewSessionTokenMethod = getRenewSessionTokenMethod = 
              io.grpc.MethodDescriptor.<com.google.cloud.broker.protobuf.RenewSessionTokenRequest, com.google.cloud.broker.protobuf.RenewSessionTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.google.cloud.broker.protobuf.Broker", "RenewSessionToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.cloud.broker.protobuf.RenewSessionTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.cloud.broker.protobuf.RenewSessionTokenResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BrokerMethodDescriptorSupplier("RenewSessionToken"))
                  .build();
          }
        }
     }
     return getRenewSessionTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.CancelSessionTokenRequest,
      com.google.cloud.broker.protobuf.CancelSessionTokenResponse> getCancelSessionTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelSessionToken",
      requestType = com.google.cloud.broker.protobuf.CancelSessionTokenRequest.class,
      responseType = com.google.cloud.broker.protobuf.CancelSessionTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.CancelSessionTokenRequest,
      com.google.cloud.broker.protobuf.CancelSessionTokenResponse> getCancelSessionTokenMethod() {
    io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.CancelSessionTokenRequest, com.google.cloud.broker.protobuf.CancelSessionTokenResponse> getCancelSessionTokenMethod;
    if ((getCancelSessionTokenMethod = BrokerGrpc.getCancelSessionTokenMethod) == null) {
      synchronized (BrokerGrpc.class) {
        if ((getCancelSessionTokenMethod = BrokerGrpc.getCancelSessionTokenMethod) == null) {
          BrokerGrpc.getCancelSessionTokenMethod = getCancelSessionTokenMethod = 
              io.grpc.MethodDescriptor.<com.google.cloud.broker.protobuf.CancelSessionTokenRequest, com.google.cloud.broker.protobuf.CancelSessionTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.google.cloud.broker.protobuf.Broker", "CancelSessionToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.cloud.broker.protobuf.CancelSessionTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.cloud.broker.protobuf.CancelSessionTokenResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BrokerMethodDescriptorSupplier("CancelSessionToken"))
                  .build();
          }
        }
     }
     return getCancelSessionTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.GetAccessTokenRequest,
      com.google.cloud.broker.protobuf.GetAccessTokenResponse> getGetAccessTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccessToken",
      requestType = com.google.cloud.broker.protobuf.GetAccessTokenRequest.class,
      responseType = com.google.cloud.broker.protobuf.GetAccessTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.GetAccessTokenRequest,
      com.google.cloud.broker.protobuf.GetAccessTokenResponse> getGetAccessTokenMethod() {
    io.grpc.MethodDescriptor<com.google.cloud.broker.protobuf.GetAccessTokenRequest, com.google.cloud.broker.protobuf.GetAccessTokenResponse> getGetAccessTokenMethod;
    if ((getGetAccessTokenMethod = BrokerGrpc.getGetAccessTokenMethod) == null) {
      synchronized (BrokerGrpc.class) {
        if ((getGetAccessTokenMethod = BrokerGrpc.getGetAccessTokenMethod) == null) {
          BrokerGrpc.getGetAccessTokenMethod = getGetAccessTokenMethod = 
              io.grpc.MethodDescriptor.<com.google.cloud.broker.protobuf.GetAccessTokenRequest, com.google.cloud.broker.protobuf.GetAccessTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.google.cloud.broker.protobuf.Broker", "GetAccessToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.cloud.broker.protobuf.GetAccessTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.cloud.broker.protobuf.GetAccessTokenResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BrokerMethodDescriptorSupplier("GetAccessToken"))
                  .build();
          }
        }
     }
     return getGetAccessTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BrokerStub newStub(io.grpc.Channel channel) {
    return new BrokerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BrokerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BrokerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BrokerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BrokerFutureStub(channel);
  }

  /**
   */
  public static abstract class BrokerImplBase implements io.grpc.BindableService {

    /**
     */
    public void getSessionToken(com.google.cloud.broker.protobuf.GetSessionTokenRequest request,
        io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.GetSessionTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSessionTokenMethod(), responseObserver);
    }

    /**
     */
    public void renewSessionToken(com.google.cloud.broker.protobuf.RenewSessionTokenRequest request,
        io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.RenewSessionTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRenewSessionTokenMethod(), responseObserver);
    }

    /**
     */
    public void cancelSessionToken(com.google.cloud.broker.protobuf.CancelSessionTokenRequest request,
        io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.CancelSessionTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelSessionTokenMethod(), responseObserver);
    }

    /**
     */
    public void getAccessToken(com.google.cloud.broker.protobuf.GetAccessTokenRequest request,
        io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.GetAccessTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccessTokenMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetSessionTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.cloud.broker.protobuf.GetSessionTokenRequest,
                com.google.cloud.broker.protobuf.GetSessionTokenResponse>(
                  this, METHODID_GET_SESSION_TOKEN)))
          .addMethod(
            getRenewSessionTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.cloud.broker.protobuf.RenewSessionTokenRequest,
                com.google.cloud.broker.protobuf.RenewSessionTokenResponse>(
                  this, METHODID_RENEW_SESSION_TOKEN)))
          .addMethod(
            getCancelSessionTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.cloud.broker.protobuf.CancelSessionTokenRequest,
                com.google.cloud.broker.protobuf.CancelSessionTokenResponse>(
                  this, METHODID_CANCEL_SESSION_TOKEN)))
          .addMethod(
            getGetAccessTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.cloud.broker.protobuf.GetAccessTokenRequest,
                com.google.cloud.broker.protobuf.GetAccessTokenResponse>(
                  this, METHODID_GET_ACCESS_TOKEN)))
          .build();
    }
  }

  /**
   */
  public static final class BrokerStub extends io.grpc.stub.AbstractStub<BrokerStub> {
    private BrokerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BrokerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BrokerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BrokerStub(channel, callOptions);
    }

    /**
     */
    public void getSessionToken(com.google.cloud.broker.protobuf.GetSessionTokenRequest request,
        io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.GetSessionTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSessionTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void renewSessionToken(com.google.cloud.broker.protobuf.RenewSessionTokenRequest request,
        io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.RenewSessionTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRenewSessionTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelSessionToken(com.google.cloud.broker.protobuf.CancelSessionTokenRequest request,
        io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.CancelSessionTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelSessionTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccessToken(com.google.cloud.broker.protobuf.GetAccessTokenRequest request,
        io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.GetAccessTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccessTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BrokerBlockingStub extends io.grpc.stub.AbstractStub<BrokerBlockingStub> {
    private BrokerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BrokerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BrokerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BrokerBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.cloud.broker.protobuf.GetSessionTokenResponse getSessionToken(com.google.cloud.broker.protobuf.GetSessionTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetSessionTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.cloud.broker.protobuf.RenewSessionTokenResponse renewSessionToken(com.google.cloud.broker.protobuf.RenewSessionTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getRenewSessionTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.cloud.broker.protobuf.CancelSessionTokenResponse cancelSessionToken(com.google.cloud.broker.protobuf.CancelSessionTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getCancelSessionTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.cloud.broker.protobuf.GetAccessTokenResponse getAccessToken(com.google.cloud.broker.protobuf.GetAccessTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccessTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BrokerFutureStub extends io.grpc.stub.AbstractStub<BrokerFutureStub> {
    private BrokerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BrokerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BrokerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BrokerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.cloud.broker.protobuf.GetSessionTokenResponse> getSessionToken(
        com.google.cloud.broker.protobuf.GetSessionTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSessionTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.cloud.broker.protobuf.RenewSessionTokenResponse> renewSessionToken(
        com.google.cloud.broker.protobuf.RenewSessionTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRenewSessionTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.cloud.broker.protobuf.CancelSessionTokenResponse> cancelSessionToken(
        com.google.cloud.broker.protobuf.CancelSessionTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelSessionTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.cloud.broker.protobuf.GetAccessTokenResponse> getAccessToken(
        com.google.cloud.broker.protobuf.GetAccessTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccessTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SESSION_TOKEN = 0;
  private static final int METHODID_RENEW_SESSION_TOKEN = 1;
  private static final int METHODID_CANCEL_SESSION_TOKEN = 2;
  private static final int METHODID_GET_ACCESS_TOKEN = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BrokerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BrokerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SESSION_TOKEN:
          serviceImpl.getSessionToken((com.google.cloud.broker.protobuf.GetSessionTokenRequest) request,
              (io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.GetSessionTokenResponse>) responseObserver);
          break;
        case METHODID_RENEW_SESSION_TOKEN:
          serviceImpl.renewSessionToken((com.google.cloud.broker.protobuf.RenewSessionTokenRequest) request,
              (io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.RenewSessionTokenResponse>) responseObserver);
          break;
        case METHODID_CANCEL_SESSION_TOKEN:
          serviceImpl.cancelSessionToken((com.google.cloud.broker.protobuf.CancelSessionTokenRequest) request,
              (io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.CancelSessionTokenResponse>) responseObserver);
          break;
        case METHODID_GET_ACCESS_TOKEN:
          serviceImpl.getAccessToken((com.google.cloud.broker.protobuf.GetAccessTokenRequest) request,
              (io.grpc.stub.StreamObserver<com.google.cloud.broker.protobuf.GetAccessTokenResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class BrokerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BrokerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.google.cloud.broker.protobuf.BrokerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Broker");
    }
  }

  private static final class BrokerFileDescriptorSupplier
      extends BrokerBaseDescriptorSupplier {
    BrokerFileDescriptorSupplier() {}
  }

  private static final class BrokerMethodDescriptorSupplier
      extends BrokerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BrokerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BrokerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BrokerFileDescriptorSupplier())
              .addMethod(getGetSessionTokenMethod())
              .addMethod(getRenewSessionTokenMethod())
              .addMethod(getCancelSessionTokenMethod())
              .addMethod(getGetAccessTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
