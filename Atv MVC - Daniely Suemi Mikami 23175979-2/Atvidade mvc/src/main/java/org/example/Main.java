package org.example;


import org.example.controller.ProductController;
import org.example.controller.UserController;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.view.MenuView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ProductRepository listaDeProdutos = null;
        UserRepository listaDeUsuarios = null;

        Connection conn = null;
        
        // Parâmetros de conexão
        String url = "jdbc:sqlite:database.sqlite";

        // Tentativa de conexão
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                ProductRepository productRepo = new ProductRepository(conn);
                UserRepository userRepo = new UserRepository(conn);

                ProductController productController = new ProductController(productRepo);
                UserController userController = new UserController(userRepo);

                new MenuView(userController, productController).showMenu();
                conn.close();
            } else {
                System.out.println("Falha na conexão.");
                System.exit(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            System.exit(1);
        }

    }
}
