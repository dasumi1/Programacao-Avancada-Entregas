package org.example.paymentMethods;

import java.util.Random;

public class BoletoPayment implements PaymentMethod {
    @Override
    public String pay(double amount) {
        System.out.println("Transação de R$ " + amount + " aceita.");
        String codigoBoleto = gerarCodigoBoleto();
        System.out.println("Código boleto: " + codigoBoleto);
        return codigoBoleto;
    }

    private String gerarCodigoBoleto() {
        Random random = new Random();
        return "Boleto" + random.nextInt(10000);
    }
}