package org.example;
import java.util.HashMap; //escolha de HashMap devido ao acesso de fácil de valores utilizando uma chave única, além de não permitir duplicatas
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        HashMap<Integer, Produto> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int opcao;


        do {
            // Exibe o menu
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Buscar produto por código");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); //consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o código do produto: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o preço do produto: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();

                    Produto produto = new Produto(nome, preco);
                    map.put(codigo, produto);
                    System.out.println("Produto cadastrado com sucesso!");

                    break;

                case 2:
                    System.out.print("Digite o código do produto para buscar: ");
                    int codigoBusca = scanner.nextInt();
                    scanner.nextLine();

                    if (map.containsKey(codigoBusca)) {
                        System.out.println("Produto correspondente encontrado: " + map.get(codigoBusca));
                    } else {
                        System.out.println("Produto não encontrado");
                    }
                    break;

                case 3:
                    System.out.println("Saindo");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 3);

        scanner.close();
    }
}