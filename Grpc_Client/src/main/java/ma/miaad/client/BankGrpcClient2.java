package ma.miaad.client;

import io.grpc.ManagedChannel;
import ma.miaad.stubs.Bank;
import ma.miaad.stubs.BankServiceGrpc;

import java.io.IOException;

public class BankGrpcClient2 {
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
        asyncStub.convert(request, new io.grpc.stub.StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                System.out.println("####################");
                System.out.println(convertCurrencyResponse);
                System.out.println("####################");

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }
        });
        System.out.println("....................");
        System.in.read();
    }
}

