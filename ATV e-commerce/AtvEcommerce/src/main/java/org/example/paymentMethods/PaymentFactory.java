package org.example.paymentMethods;

import static org.example.paymentMethods.PaymentType.PIX;

public class PaymentFactory {

    public static PaymentMethod createPayment(PaymentType type) {
        switch (type) {
            case PIX:
                return new PixPayment();
            case CARTAO:
                return new CreditCardPayment();
            case BOLETO:
                return new BoletoPayment();
            default:
                throw new IllegalArgumentException("Forma de pagamento inválida.");
        }
    }
}
