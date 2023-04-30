package ma.Game.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessNumberGameServer {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 1000;
    private int secretNumber;
    private List<StreamObserver<GuessResponse>> observers = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        GuessNumberGameServer server = new GuessNumberGameServer();
        Server grpcServer = ServerBuilder.forPort(9000).addService(new GuessNumberGameImpl(server)).build();

        System.out.println("Starting server...");
        grpcServer.start();
        System.out.println("Server started");

        server.secretNumber = new Random().nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        System.out.println("Secret number is: " + server.secretNumber);
        grpcServer.awaitTermination();
    }

    public void handleGuess(GuessRequest guess, StreamObserver<GuessResponse> responseObserver) {
        int number = guess.getNumber();
        String message = "End of the game";

        if (number < secretNumber) {
            message = "\nYour number is smaller";
            responseObserver.onNext(GuessResponse.newBuilder().setMessage(message).build());
        } else if (number > secretNumber) {
            message = "\nYour number is bigger";
            responseObserver.onNext(GuessResponse.newBuilder().setMessage(message).build());
        } else if (number == secretNumber){
            int winner = observers.size() + 1;
            message = "Congratulations! The User Number " + winner + " guessed the number";

            for (StreamObserver<GuessResponse> observer : observers) {
                observer.onNext(GuessResponse.newBuilder().setMessage(message).build());
            }

            responseObserver.onCompleted();
            return;
        }

        observers.add(responseObserver);

    }


    private static class GuessNumberGameImpl extends GuessNumberGameGrpc.GuessNumberGameImplBase {
        private final GuessNumberGameServer server;

        private GuessNumberGameImpl(GuessNumberGameServer server) {
            this.server = server;
        }

        @Override
        public StreamObserver<GuessRequest> guessNumber(StreamObserver<GuessResponse> responseObserver) {
            return new StreamObserver<GuessRequest>() {
                @Override
                public void onNext(GuessRequest guess) {
                    server.handleGuess(guess, responseObserver);
                    System.out.println("Client guessed: " + guess.getNumber());
                }

                @Override
                public void onError(Throwable throwable) {
                    responseObserver.onCompleted();
                    System.out.println("END");
                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                    System.out.println("Restart The Game!");

                }

            };
        }
    }
}
