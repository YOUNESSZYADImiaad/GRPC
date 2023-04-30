package ChatGRPC.chatstubs.Server;

import ChatGRPC.chatstubs.Chat;
import ChatGRPC.chatstubs.ChatServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    private final int port;
    private final Map<String, StreamObserver<Chat.Message>> users = new HashMap<>();

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port)
                .addService(new ChatServiceImpl())
                .build()
                .start();

        System.out.println("Server started on port " + port);

        server.awaitTermination();
    }

    private class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {
     public void sendMessage(Chat.Message request, StreamObserver<Chat.Message> responseObserver) {
         String username = request.getUsername();

         if (!users.containsKey(username)) {
             users.put(username, responseObserver);

             System.out.println(username + " joined the chat");

             Chat.Message welcomeMessage = Chat.Message.newBuilder()
                     .setUsername("Server")
                     .setText("Welcome to the chat, " + username)
                     .build();

                 responseObserver.onNext(welcomeMessage);
                 responseObserver.onCompleted();

         } else {
             System.out.println(username + ": " + request.getText());
             responseObserver.onNext(request);
             responseObserver.onCompleted();
         }
     }
 }

    public static void main(String[] args) throws IOException, InterruptedException {
         ChatServer server = new ChatServer(5555);
         server.start();
     }
}

