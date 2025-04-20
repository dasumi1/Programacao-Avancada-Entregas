package org.example.paymentMethods;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public String pay(double amount) {
        String cardNumber = "1234-5678-9012-3456";
        System.out.println("Pagamento no valor de R$" + amount + " processado com o cartão " + cardNumber);
        return cardNumber;
    }
}