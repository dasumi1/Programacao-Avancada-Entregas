package org.example;

public class Onibus extends Veiculo{

    private int quantidadeEixos;

    public Onibus(String marca, String modelo, String combustivel, int ano, int capacidadePassageiros, int quantidadeEixos) {
        super(marca, modelo, combustivel, ano, capacidadePassageiros);

        if (quantidadeEixos < 6 || quantidadeEixos > 8 ){
            System.out.println("Quantidade de eixos inválida");
        } else {
            this.quantidadeEixos = quantidadeEixos;
        }
    }

    @Override
    public double calcularAutonomia() {
        double tanque = 200;
        double consumo = 5;
        return tanque * consumo;
    }
}
