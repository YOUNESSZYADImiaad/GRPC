package ma.miaad.client;

import io.grpc.ManagedChannel;
import ma.miaad.stubs.Bank;
import ma.miaad.stubs.BankServiceGrpc;

public class BankGrpcClient1 {
    public static void main(String[] args) {
        ManagedChannel channel = io.grpc.ManagedChannelBuilder.forAddress("localhost", 5555)
            .usePlaintext()
            .build();

        BankServiceGrpc.BankServiceBlockingStub blockingstub = BankServiceGrpc.newBlockingStub(channel);
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
            .setCurrencyFrom("USD")
            .setCurrencyTo("MAD")
            .setAmount(100)
            .build();
        Bank.ConvertCurrencyResponse response = blockingstub.convert(request);
        System.out.println(response);
    }
}
