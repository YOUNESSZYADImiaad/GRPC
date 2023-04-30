package ma.miaad.Server;

import io.grpc.Server;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = io.grpc.ServerBuilder.forPort(5555)
            .addService(new ma.miaad.service.BankGrpcService())
            .build();
        server.start();
        server.awaitTermination();
    }
}
