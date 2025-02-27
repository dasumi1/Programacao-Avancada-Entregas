package org.example;

public class CaminhaoRefrigerado extends Caminhao {
    private double temperaturaMinima;

    public CaminhaoRefrigerado(String marca, String modelo, String combustivel, int ano, int capacidadePassageiros, double capacidadeCarga, double temperaturaMinima) {
        super(marca, modelo, combustivel, ano, capacidadePassageiros, capacidadeCarga);
        this.temperaturaMinima = temperaturaMinima;
    }

    @Override
    public double calcularAutonomia() {
        return super.calcularAutonomia() * 0.9;
    }

}