package org.example;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processTransaction(double amount) {
        if (paymentStrategy != null) {
            paymentStrategy.processPayment(amount);
        } else {
            System.out.println("Nenhum método de pagamento selecionado.");
        }
    }
}
