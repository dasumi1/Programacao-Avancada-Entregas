package org.example;

public class CarroEletrico extends Carro{
    private double bateriaKWh;

    public CarroEletrico(String marca, String modelo, String combustivel, int ano, int capacidadePassageiros, TipoCarro tipoCarro, double bateriaKWh) {
        super(marca, modelo, combustivel, ano, capacidadePassageiros, tipoCarro);
        this.bateriaKWh = bateriaKWh;
    }

    @Override
    public double calcularAutonomia() {
        double consumo = 5;
        return bateriaKWh * consumo;
    }
}
