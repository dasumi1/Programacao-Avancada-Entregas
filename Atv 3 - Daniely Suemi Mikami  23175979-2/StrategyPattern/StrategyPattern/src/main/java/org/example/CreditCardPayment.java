package org.example;

import java.util.Random;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        String cardNumber = "1234-5678-9012-3456";
        System.out.println("Pagamento no valor de R$" + amount + " processado com o cartão " + cardNumber);
    }


}
