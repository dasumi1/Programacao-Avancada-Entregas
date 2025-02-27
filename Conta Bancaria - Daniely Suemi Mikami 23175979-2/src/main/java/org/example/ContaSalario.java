package org.example;

public class ContaSalario extends ContaCorrente{
    private boolean saqueGratuitoMes = true;

    public ContaSalario(String numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
    }

   double sacar(double valor) {
       if (saldo >= valor) {
           if (saqueGratuitoMes) {
               saldo -= valor;
               saqueGratuitoMes = false;
               System.out.println("Saque de R$ " + valor + "realizado com sucesso!");
           } else {
               saldo -= (valor + 5.00);
               System.out.println("Saque de R$" + valor + " realizado com taxa de R$ 5,00.");
           }
       }  else {
           System.out.println("Saldo insuficiente!");
       }

       return 0;
   }
}
