package org.example;

public class Caminhao extends Veiculo{
    private double capacidadeCarga;

    public Caminhao(String marca, String modelo, String combustivel, int ano, int capacidadePassageiros, double capacidadeCarga) {
        super(marca, modelo, combustivel, ano, capacidadePassageiros);
        this.capacidadeCarga = capacidadeCarga;
    }

    @Override
    public double calcularAutonomia() {
        double tanque = 300;
        double consumoBase = 6;
        double reducaoConsumo = Math.min(capacidadeCarga * 0.01, 0.25);
        double consumoFinal = consumoBase * (1 - reducaoConsumo);
        return tanque * consumoFinal;
    }

}
