package org.example;

public class ContaInvestimento extends ContaBancaria{
    protected double taxaRetirada = 0.02;

    public ContaInvestimento(String numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
    }

    @Override
    double sacar(double valor) {
        if (valor <= saldo) {
            saldo -= (valor + (valor * taxaRetirada));
            System.out.println("Saque de R$ " + valor + "realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente!");
        }
        return 0;
    }
}
