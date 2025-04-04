package org.example;

import org.example.entities.User;
import org.example.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserRepository listaDeUsuarios = null;
        Connection conn = null;

        String url = "jdbc:sqlite:database.sqlite";

        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                listaDeUsuarios = new UserRepository(conn);
            } else {
                System.out.println("Falha na conexão.");
                System.exit(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n---MENU---");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Sair");
            System.out.println("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Cadastrar Usuário");
                    listaDeUsuarios.save(new User("Teste", "teste@testando.com", "senhateste"));
                    listaDeUsuarios.save(new User("Fulaninho", "fulano@gmail.com", "fulaninho123"));
                    listaDeUsuarios.save(new User("Ciclano", "ciclano@gmail.com", "ciclaninhoinho4321"));
                    System.out.println("Usuários salvos!"); // Confirmação
                    break;
                case 2:
                    System.out.println("Listar Usuários");
                    List<User> users = listaDeUsuarios.findAll();
                    if (users.isEmpty()) {
                        System.err.println("Nenhum usuário encontrado!"); // Alerta
                    }
                    users.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
                    break;
            }

        } while (option != 3);

        scanner.close();
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}