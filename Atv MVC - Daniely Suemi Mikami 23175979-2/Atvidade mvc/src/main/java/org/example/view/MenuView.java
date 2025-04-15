package org.example.view;

import org.example.controller.ProductController;
import org.example.controller.UserController;

import java.util.Scanner;

public class MenuView {
    private final UserController userController;
    private final ProductController productController;
    private final Scanner scanner;

    public MenuView(UserController userController, ProductController productController) {
        this.userController = userController;
        this.productController = productController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int option;
        do {
            printMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer

            switch (option) {
                case 1 -> registerProduct();
                case 2 -> listProducts();
                case 3 -> registerUser();
                case 4 -> listUsers();
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (option != 5);
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n---MENU---");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Listas Produtos");
        System.out.println("3 - Cadastrar Usuário");
        System.out.println("4 - Listar Usuários");
        System.out.println("5 - Sair");
        System.out.println("Escolha uma opção: ");
    }

    private void registerProduct() {
        System.out.print("Nome do produto: ");
        String name = scanner.nextLine();
        System.out.print("Preço: ");
        double price = scanner.nextDouble();
        productController.registerProduct(name, price);
        System.out.println("Produto cadastrado!");
    }

    private void listProducts() {
        System.out.println("\nProdutos Cadastrados:");
        productController.getAllProducts().forEach(System.out::println);
    }

    private void registerUser() {
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();
        userController.registerUser(name, email, password);
        System.out.println("Usuário cadastrado!");
    }

    private void listUsers() {
        System.out.println("\nUsuários Cadastrados:");
        userController.listAllUsers().forEach(System.out::println);
    }
}
