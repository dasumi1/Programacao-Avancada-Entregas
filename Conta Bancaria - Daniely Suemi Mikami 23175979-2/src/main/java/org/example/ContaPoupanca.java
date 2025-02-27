package org.example;

public class ContaPoupanca extends ContaBancaria{

    public ContaPoupanca(String numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
    }

    @Override
    double sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + "realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente!");
        }
        return 0;
    }
}
