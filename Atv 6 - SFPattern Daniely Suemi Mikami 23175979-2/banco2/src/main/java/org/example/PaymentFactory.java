package org.example;

public class PaymentFactory {

    public static PaymentStrategy createPayment(String type) { //decide qual estratégia de pagamentoinstanciar  com base na escolha do usuário na main
        switch (type) { //recebe entrada do Main
            case "1":
                return new PixPayment(); //se escolher 1, cria um objeto da classe PixPayment
            case "2":
                return new CreditCardPayment();
            case "3":
                return new BoletoPayment();
            default:
                throw new IllegalArgumentException("Tipo de pagamento inválido."); //caso contrário, retorna mensagem de entrada inválida
        }
    }
}
