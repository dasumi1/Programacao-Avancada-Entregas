package org.example;
import java.util.Random;

public class BoletoPayment implements PaymentStrategy{
    @Override
    public void processPayment(double amount) {
        System.out.println("Transação de R$" + amount + "aceita.");
        String codigoBoleto = gerarCodigoBoleto();
        System.out.println("Código boleto: " + codigoBoleto);
    }

    private String gerarCodigoBoleto() {
        Random random = new Random();
        return "Boleto" + random.nextInt(10000);
    }
}
