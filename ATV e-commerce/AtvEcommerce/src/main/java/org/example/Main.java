package org.example;

import org.example.entities.Product;
import org.example.entities.Sales;
import org.example.entities.User;
import org.example.paymentMethods.PaymentFactory;
import org.example.paymentMethods.PaymentMethod;
import org.example.paymentMethods.PaymentProcessor;
import org.example.paymentMethods.PaymentType;
import org.example.repository.ProductRepository;
import org.example.repository.SalesRepository;
import org.example.repository.UserRepository;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:database.sqlite";

        try (Connection conn = DriverManager.getConnection(url);
             Scanner scanner = new Scanner(System.in)) {

            criarTabelas(conn);

            ProductRepository produtoRepo = new ProductRepository(conn);
            UserRepository usuarioRepo = new UserRepository(conn);
            SalesRepository vendaRepo = new SalesRepository(conn);

            int opcao;
            do {
                System.out.println("\n=== SISTEMA DE E-COMMERCE ===");
                System.out.println("1. Cadastrar Produto");
                System.out.println("2. Listar Produtos");
                System.out.println("3. Cadastrar Usuário");
                System.out.println("4. Listar Usuários");
                System.out.println("5. Realizar Venda");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> cadastrarProduto(scanner, produtoRepo);
                    case 2 -> listarProdutos(produtoRepo);
                    case 3 -> cadastrarUsuario(scanner, usuarioRepo);
                    case 4 -> listarUsuarios(usuarioRepo);
                    case 5 -> realizarVenda(scanner, usuarioRepo, produtoRepo, vendaRepo);
                    case 6 -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            } while (opcao != 6);

        } catch (SQLException e) {
            System.err.println("Erro no banco de dados: " + e.getMessage());
        }
    }

    private static void criarTabelas(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (" +
                    "uuid TEXT PRIMARY KEY, " +
                    "id TEXT NOT NULL UNIQUE, " +
                    "name TEXT NOT NULL, " +
                    "price REAL NOT NULL)");

            // Tabela de usuários
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "uuid TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT NOT NULL UNIQUE, " +
                    "password TEXT NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS sales (" +
                    "id TEXT PRIMARY KEY, " +
                    "user_id TEXT NOT NULL, " +
                    "payment_method TEXT NOT NULL, " +
                    "sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "total_amount REAL NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(uuid))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS sale_products (" +
                    "sale_id TEXT NOT NULL, " +
                    "product_id TEXT NOT NULL, " +
                    "PRIMARY KEY (sale_id, product_id), " +
                    "FOREIGN KEY (sale_id) REFERENCES sales(id), " +
                    "FOREIGN KEY (product_id) REFERENCES products(uuid))");
        }
    }

    private static void cadastrarProduto(Scanner scanner, ProductRepository repo) {
        System.out.println("\n--- CADASTRO DE PRODUTO ---");
        int id;
        while (true) {
            System.out.print("ID do produto (número inteiro): ");
            try {
                id = Integer.parseInt(scanner.nextLine());

                if (repo.findByNumericId(id).isPresent()) {
                    System.out.println("Este ID já está em uso!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Número inválido!");
            }
        }

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        repo.save(new Product(id, nome, preco));
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarProdutos(ProductRepository repo) {
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        List<Product> produtos = repo.findAll();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(p -> System.out.printf("%s - R$%.2f (ID: %s)%n",
                    p.getName(), p.getPrice(), p.getId()));
        }
    }

    private static void cadastrarUsuario(Scanner scanner, UserRepository repo) {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        repo.save(new User(nome, email, senha));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void listarUsuarios(UserRepository repo) {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        List<User> usuarios = repo.findAll();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            usuarios.forEach(u -> System.out.printf("%s - %s (ID: %s)%n",
                    u.getUserName(), u.getEmail(), u.getUuid()));
        }
    }

    private static void realizarVenda(Scanner scanner, UserRepository usuarioRepo,
                                      ProductRepository produtoRepo, SalesRepository vendaRepo) {
        System.out.println("\n--- REGISTRAR VENDA ---");

        System.out.print("Email do cliente: ");
        String email = scanner.nextLine();
        Optional<User> usuario = usuarioRepo.findByEmail(email);

        if (usuario.isEmpty()) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        System.out.println("Cliente: " + usuario.get().getUserName());

        System.out.print("IDs dos produtos (separados por vírgula): ");
        String[] idsProdutos = scanner.nextLine().split(",");

        List<Product> produtos = new ArrayList<>();
        double total = 0.0;

        for (String idStr : idsProdutos) {
            try {
                int id = Integer.parseInt(idStr.trim());
                Optional<Product> produto = produtoRepo.findByNumericId(id);

                if (produto.isPresent()) {
                    produtos.add(produto.get());
                    total += produto.get().getPrice();
                    System.out.println(produto.get());
                } else {
                    System.out.println("Produto ID " + id + " não encontrado");
                }
            } catch (NumberFormatException e) {
                System.out.println("ID inválido: " + idStr);
            }
        }

        PaymentType formaPagamento = selecionarFormaPagamento(scanner);
        if (formaPagamento == null) return;

        processarPagamento(formaPagamento, total);

        Sales venda = new Sales(formaPagamento, usuario.get(), produtos);
        vendaRepo.save(venda);

        exibirComprovante(usuario.get(), produtos, total, formaPagamento);
    }

    private static PaymentType selecionarFormaPagamento(Scanner scanner) {
        while (true) {
            System.out.println("\n--- FORMA DE PAGAMENTO ---");
            System.out.println("1 - Cartão de Crédito");
            System.out.println("2 - Boleto Bancário");
            System.out.println("3 - PIX");
            System.out.println("0 - Cancelar");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> { return PaymentType.CARTAO; }
                case "2" -> { return PaymentType.BOLETO; }
                case "3" -> { return PaymentType.PIX; }
                case "0" -> {
                    System.out.println("Operação cancelada.");
                    return null;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void processarPagamento(PaymentType formaPagamento, double total) {
        PaymentMethod metodo = PaymentFactory.createPayment(formaPagamento);
        String resultado = new PaymentProcessor(metodo).processTransaction(total);
        System.out.println("\nEfetuando pagamento...");
        System.out.println(resultado);
    }

    private static void exibirComprovante(User cliente, List<Product> produtos, double total, PaymentType formaPagamento) {
        System.out.println("\nResumo da venda:");
        System.out.println("Cliente: " + cliente.getUserName());
        System.out.println("\nItens::");
        produtos.forEach(p -> System.out.printf("- %s", p.getName()));
        System.out.printf("\nTotal: R$%.2f%n", total);
        System.out.println("Pagamento: " + formaPagamento);
        System.out.println("Venda registrada com sucesso!");
    }
}
