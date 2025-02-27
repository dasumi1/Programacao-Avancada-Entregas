package org.example;

public class ContaInvestimentoAltoRisco extends ContaInvestimento{
    private double saldoMinimoRetirada = 10000.00;

    public ContaInvestimentoAltoRisco(String numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
        this.taxaRetirada = 0.05;
    }

    @Override
    double sacar(double valor) {
        double valorComTaxa = valor + (valor * taxaRetirada);

        if (saldo >= valorComTaxa && (saldo - valorComTaxa) >= saldoMinimoRetirada) {
            saldo -= valorComTaxa;
            System.out.println("Saque de R$ " + valor + "realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente!");
        }
        return 0;
    }
}
