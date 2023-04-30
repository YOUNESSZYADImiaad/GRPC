import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ma.Game.grpc.GuessNumberGameGrpc;
import ma.Game.grpc.GuessRequest;
import ma.Game.grpc.GuessResponse;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GuessNumberGameClient {
    private final ManagedChannel channel;
    private final GuessNumberGameGrpc.GuessNumberGameStub stub;

    public GuessNumberGameClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        stub = GuessNumberGameGrpc.newStub(channel);
    }
    private static int hasWinner(GuessResponse response) {
        int winner = response.getWinner();
        return winner != 0 ? winner : -1;
    }


    public void startGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<GuessRequest> requestObserver = stub.guessNumber(new StreamObserver<GuessResponse>() {
            @Override
            public void onNext(GuessResponse response) {
                System.out.println(response.getMessage());
                int winner = hasWinner(response);
                if (winner != -1) {
                    System.out.println("Player " + winner + " wins!");
                    latch.countDown();
                }
            }


            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
                channel.shutdown();
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Game over!");
                channel.shutdown();
                latch.countDown();
            }
        });

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your guess (between 1 and 1000):");
            int number = scanner.nextInt();

            requestObserver.onNext(GuessRequest.newBuilder().setNumber(number).build());

            if (latch.getCount() == 0) {
                break;
            }
        }

        requestObserver.onCompleted();

        latch.await();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        String host = "localhost";
        int port = 9000;

        GuessNumberGameClient client = new GuessNumberGameClient(host, port);

        try {
            client.startGame();
        } finally {
            client.shutdown();
        }
    }
}