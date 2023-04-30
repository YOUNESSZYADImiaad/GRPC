package ma.miaad.client;

import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import ma.miaad.stubs.Bank;
import ma.miaad.stubs.BankServiceGrpc;

import java.io.IOException;
import java.util.Timer;

public class BankGrpcClient5 {
    public static void main(String[] args) throws IOException {
        ManagedChannel channel = io.grpc.ManagedChannelBuilder.forAddress("localhost", 5555)
                .usePlaintext()
                .build();

        BankServiceGrpc.BankServiceStub asyncStub = BankServiceGrpc.newStub(channel);
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                .setCurrencyFrom("USD")
                .setCurrencyTo("MAD")
                .setAmount(100)
                .build();
        StreamObserver<Bank.ConvertCurrencyRequest> fullCurrencyStream =
                asyncStub.fullCurrencyStream(new StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-");
                System.out.println(convertCurrencyResponse);
                System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        });
        Timer timer = new Timer();
        timer.schedule(new java.util.TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                        .setCurrencyFrom("USD")
                        .setCurrencyTo("MAD")
                        .setAmount(Math.random()*100)
                        .build();
                fullCurrencyStream.onNext(request);
                // show the progress of the stream using the counter
                System.out.println("=====> counter = " + counter);
                ++counter;
                if (counter == 20) {
                    fullCurrencyStream.onCompleted();
                    timer.cancel();
                }
            }
        }, 1000, 1000);
        System.out.println("....................");
        System.in.read();
    }
}

