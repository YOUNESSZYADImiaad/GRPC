package ma.Game.grpc;

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
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: game.proto")
public final class GuessNumberGameGrpc {

  private GuessNumberGameGrpc() {}

  public static final String SERVICE_NAME = "GuessNumberGame";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ma.Game.grpc.GuessRequest,
      ma.Game.grpc.GuessResponse> getGuessNumberMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GuessNumber",
      requestType = ma.Game.grpc.GuessRequest.class,
      responseType = ma.Game.grpc.GuessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<ma.Game.grpc.GuessRequest,
      ma.Game.grpc.GuessResponse> getGuessNumberMethod() {
    io.grpc.MethodDescriptor<ma.Game.grpc.GuessRequest, ma.Game.grpc.GuessResponse> getGuessNumberMethod;
    if ((getGuessNumberMethod = GuessNumberGameGrpc.getGuessNumberMethod) == null) {
      synchronized (GuessNumberGameGrpc.class) {
        if ((getGuessNumberMethod = GuessNumberGameGrpc.getGuessNumberMethod) == null) {
          GuessNumberGameGrpc.getGuessNumberMethod = getGuessNumberMethod = 
              io.grpc.MethodDescriptor.<ma.Game.grpc.GuessRequest, ma.Game.grpc.GuessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "GuessNumberGame", "GuessNumber"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ma.Game.grpc.GuessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ma.Game.grpc.GuessResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GuessNumberGameMethodDescriptorSupplier("GuessNumber"))
                  .build();
          }
        }
     }
     return getGuessNumberMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GuessNumberGameStub newStub(io.grpc.Channel channel) {
    return new GuessNumberGameStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GuessNumberGameBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GuessNumberGameBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GuessNumberGameFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GuessNumberGameFutureStub(channel);
  }

  /**
   */
  public static abstract class GuessNumberGameImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<ma.Game.grpc.GuessRequest> guessNumber(
        io.grpc.stub.StreamObserver<ma.Game.grpc.GuessResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGuessNumberMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGuessNumberMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                ma.Game.grpc.GuessRequest,
                ma.Game.grpc.GuessResponse>(
                  this, METHODID_GUESS_NUMBER)))
          .build();
    }
  }

  /**
   */
  public static final class GuessNumberGameStub extends io.grpc.stub.AbstractStub<GuessNumberGameStub> {
    private GuessNumberGameStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GuessNumberGameStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GuessNumberGameStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GuessNumberGameStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ma.Game.grpc.GuessRequest> guessNumber(
        io.grpc.stub.StreamObserver<ma.Game.grpc.GuessResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGuessNumberMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GuessNumberGameBlockingStub extends io.grpc.stub.AbstractStub<GuessNumberGameBlockingStub> {
    private GuessNumberGameBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GuessNumberGameBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GuessNumberGameBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GuessNumberGameBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class GuessNumberGameFutureStub extends io.grpc.stub.AbstractStub<GuessNumberGameFutureStub> {
    private GuessNumberGameFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GuessNumberGameFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GuessNumberGameFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GuessNumberGameFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GUESS_NUMBER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GuessNumberGameImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GuessNumberGameImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GUESS_NUMBER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.guessNumber(
              (io.grpc.stub.StreamObserver<ma.Game.grpc.GuessResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GuessNumberGameBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GuessNumberGameBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ma.Game.grpc.Game.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GuessNumberGame");
    }
  }

  private static final class GuessNumberGameFileDescriptorSupplier
      extends GuessNumberGameBaseDescriptorSupplier {
    GuessNumberGameFileDescriptorSupplier() {}
  }

  private static final class GuessNumberGameMethodDescriptorSupplier
      extends GuessNumberGameBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GuessNumberGameMethodDescriptorSupplier(String methodName) {
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
      synchronized (GuessNumberGameGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GuessNumberGameFileDescriptorSupplier())
              .addMethod(getGuessNumberMethod())
              .build();
        }
      }
    }
    return result;
  }
}
