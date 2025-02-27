package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ContaBancaria contaCorrente = new ContaCorrente("1", "Celso", 1000.00);
        ContaBancaria contaPoupanca= new ContaPoupanca("2", "Celsa", 1000.00);
        ContaBancaria contaInvestimento = new ContaCorrente("3", "Celson", 10000.00);
        ContaBancaria contaSalario = new ContaSalario("4","Celsana", 1000.00);
        ContaBancaria contaInvestAltoRisco = new ContaInvestimentoAltoRisco("5", "Celfather", 1000.00);

        contaCorrente.depositar(500.00);
        contaPoupanca.depositar(100.00);
        contaInvestimento.depositar(100.00);
        contaSalario.depositar(100.00);
        contaInvestAltoRisco.depositar(100.00);
        contaCorrente.sacar(1000.00);
        contaPoupanca.sacar(1500.00);
        contaInvestimento.sacar(200.00);
        contaSalario.sacar(2000.00);
        contaInvestAltoRisco.sacar(2000.00);

        contaCorrente.exibirInformacoes();
        contaPoupanca.exibirInformacoes();
        contaInvestimento.exibirInformacoes();
        contaSalario.exibirInformacoes();
        contaInvestAltoRisco.exibirInformacoes();

    }
}