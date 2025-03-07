package org.example;

public class Main {
    public static void main(String[] args) {
        double amount = 150.50;

        PaymentProcessor paymentProcessor = new PaymentProcessor(new PixPayment());
        paymentProcessor.processTransaction(amount);

        //PaymentProcessor paymentProcessor = new PaymentProcessor(new BoletoPayment());
        //paymentProcessor.processTransaction(amount);

        //PaymentProcessor paymentProcessor = new PaymentProcessor(new CreditCardPayment());
        //paymentProcessor.processTransaction(amount);

    }
}
