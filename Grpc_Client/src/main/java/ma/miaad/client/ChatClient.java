package ma.miaad.client;

import ChatGRPC.chatstubs.Chat;
import ChatGRPC.chatstubs.ChatServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;

public class ChatClient {
    private final String username;
    private final ChatServiceGrpc.ChatServiceStub stub;

    public ChatClient(String host, int port, String username) {
        this.username = username;

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        this.stub = ChatServiceGrpc.newStub(channel);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            if (text.isEmpty()) {
                continue;
            }

            Chat.Message message = Chat.Message.newBuilder()
                    .setUsername(username)
                    .setText(text)
                    .build();
        StreamObserver<Chat.Message> observer = new StreamObserver<Chat.Message>() {
            @Override
            public void onNext(Chat.Message message) {
                if (!message.getUsername().equals(username)) {
                    System.out.println(message.getUsername() + ": " + message.getText());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("Error: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Server closed the stream");
            }
        };
            StreamObserver<Chat.Message> requestObserver = stub.sendMessage(message,observer);
            requestObserver.onNext(message);
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient("localhost", 5555, "Java");
        client.start();
    }
}

