package org.example;

import java.util.Random;

public class PixPayment implements PaymentStrategy{
    @Override
    public void processPayment(double amount) {
        String codigoPix = gerarCodigoPix();
        System.out.println("Transação de R$" + amount + "aceita.");
        System.out.println("Código pix gerado: " + codigoPix);
    }

    private String gerarCodigoPix(){
        Random random = new Random();
        return "PIX" + random.nextInt(100000);
    }
}
