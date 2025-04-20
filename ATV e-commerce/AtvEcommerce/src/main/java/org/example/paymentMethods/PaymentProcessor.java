package org.example.paymentMethods;

public class PaymentProcessor {
    private PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String processTransaction(double amount) {
        if (paymentMethod != null) {
           return "Pagamento de R$ " + paymentMethod.pay(amount) + "Processado com sucesso";
        } else {
            return "Nenhum método de pagamento selecionado.";
        }

    }
}
