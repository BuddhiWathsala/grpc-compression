import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

import java.io.IOException;

public class HelloWorldServer {

    private static final int PORT = 50052;
    private Server server;

    public void start() throws IOException {

        server = ServerBuilder.forPort(PORT)
                .intercept(new ServerInterceptor() {
                    @Override
                    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
                                                                                 ServerCallHandler<ReqT, RespT> next) {

                        call.setCompression("gzip");
                        return next.startCall(call, headers);
                    }
                })
                .maxInboundMessageSize(6455878)
                .addService(new HelloWorldServiceImpl())
                .build()
                .start();

    }

    public void blockUntilShutdown() throws InterruptedException {

        if (server == null) {
            return;
        }

        server.awaitTermination();
    }

    public static void main(String[] args)
            throws InterruptedException, IOException {

        HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }
}
