package org.example.paymentMethods;

import java.util.Random;

public class PixPayment implements PaymentMethod{
    @Override
    public String pay(double amount) {
        String codigoPix = gerarCodigoPix();
        System.out.println("Transação de R$" + amount + " aceita.");
        System.out.println("Código pix gerado: " + codigoPix);
        return codigoPix;
    }

    private String gerarCodigoPix(){
        Random random = new Random();
        return "PIX" + random.nextInt(100000);
    }
}
