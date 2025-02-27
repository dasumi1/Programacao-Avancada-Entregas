package org.example;

abstract public class ContaBancaria {
    protected String numeroConta;
    protected String titular;
    protected double saldo;

    public ContaBancaria(String numeroConta, String titular, double saldo) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
    }

   public double depositar(double valor) {
        return this.saldo += valor;
    }

    abstract double sacar(double valor);

    public void exibirInformacoes(){
        System.out.println("Numero Conta: " + this.numeroConta);
        System.out.println("Titular: " + this.titular);
        System.out.println("Saldo: " + this.saldo);
    }
}
