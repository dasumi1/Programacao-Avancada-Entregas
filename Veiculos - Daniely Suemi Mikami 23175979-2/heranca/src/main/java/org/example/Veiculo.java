package org.example;

public abstract class Veiculo {
    private String marca, modelo, combustivel;
    private int ano, capacidadePassageiros;

    public Veiculo(String marca, String modelo, String combustivel, int ano, int capacidadePassageiros) {
        this.marca = marca;
        this.modelo = modelo;
        this.combustivel = combustivel;
        this.ano = ano;
        this.capacidadePassageiros = capacidadePassageiros;
    }

  public abstract double calcularAutonomia();

    public void exibirDetalhes() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Combustivel: " + combustivel);
        System.out.println("Ano: " + ano);
        System.out.println("CapacidadePassageiros: " + capacidadePassageiros);
        System.out.println("Autonomia: " + calcularAutonomia());
    }

}
