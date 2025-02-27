package org.example;

public class Carro extends Veiculo {
    private TipoCarro tipoCarro;

    public Carro(String marca, String modelo, String combustivel, int ano, int capacidadePassageiros, TipoCarro tipoCarro) {
        super(marca, modelo, combustivel, ano, capacidadePassageiros);
        this.tipoCarro = tipoCarro;
    }

    @Override
    public double calcularAutonomia() {
        double tanque = 50;
        double consumo = 12;
        return tanque * consumo;
    }

}
