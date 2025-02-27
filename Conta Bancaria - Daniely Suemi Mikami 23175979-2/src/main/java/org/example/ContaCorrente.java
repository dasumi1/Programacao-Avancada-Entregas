package org.example;

public class ContaCorrente extends ContaBancaria{
    protected double limiteCheque = 50000.00;

    public ContaCorrente(String numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
    }

    @Override
    double sacar(double valor) {
        if (saldo >= valor){
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + "realizado com sucesso!");
        } else  if ((saldo + limiteCheque) >= valor) {
            double diferenca = valor - saldo;
            saldo = 0;
            limiteCheque -= diferenca;
            System.out.println("Saque de R$ " + valor + "realizado com sucesso! Uso do cheque especial!");
        } else {
            System.out.println("Saldo insuficiente!");
        }
        return 0;
    }
}
