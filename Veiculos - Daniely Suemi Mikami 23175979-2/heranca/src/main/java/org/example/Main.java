package org.example;

import static org.example.TipoCarro.SEDAN;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Carro carro = new Carro("Toyota", "Corolla", "Gasolina", 2022, 5, SEDAN);
        Caminhao caminhao = new Caminhao("Volvo", "FH16", "Diesel", 2021, 2, 10);
        Onibus onibus = new Onibus("Mercedes", "Benz", "Diesel", 2020, 40, 6);
        CarroEletrico carroEletrico = new CarroEletrico("Tesla", "Model S", "Eletricidade", 2023, 5, SEDAN, 100);
        CaminhaoRefrigerado caminhaoRefrigerado = new CaminhaoRefrigerado("Scania", "R500", "Diesel", 2021, 2, 15, -10);

        carro.exibirDetalhes();
        System.out.println("Autonomia: " + carro.calcularAutonomia() + " km\n");

        caminhao.exibirDetalhes();
        System.out.println("Autonomia: " + caminhao.calcularAutonomia() + " km\n");

        onibus.exibirDetalhes();
        System.out.println("Autonomia: " + onibus.calcularAutonomia() + " km\n");

        carroEletrico.exibirDetalhes();
        System.out.println("Autonomia: " + carroEletrico.calcularAutonomia() + " km\n");

        caminhaoRefrigerado.exibirDetalhes();
        System.out.println("Autonomia: " + caminhaoRefrigerado.calcularAutonomia() + " km\n");
    }
}

