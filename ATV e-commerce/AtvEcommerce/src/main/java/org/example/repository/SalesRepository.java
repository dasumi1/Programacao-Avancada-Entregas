package org.example.repository;

import org.example.entities.Sales;
import org.example.entities.Product;
import java.sql.*;
import java.util.*;

public class SalesRepository implements EntityRepository<Sales> {
    private final Connection connection;

    public SalesRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Sales sale) {
        try {
            connection.setAutoCommit(false);

            String saleSQL = "INSERT INTO sales (id, user_id, payment_method, sale_date, total_amount) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(saleSQL)) {
                stmt.setString(1, sale.getUuid().toString());
                stmt.setString(2, sale.getUser().getUuid().toString());
                stmt.setString(3, sale.getPaymentType().name());
                stmt.setTimestamp(4, new Timestamp(sale.getSaleDate().getTime()));
                stmt.setDouble(5, sale.getTotalAmount());
                stmt.executeUpdate();
            }

            String productsSQL = "INSERT INTO sale_products (sale_id, product_id) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(productsSQL)) {
                for (Product product : sale.getProducts()) {
                    stmt.setString(1, sale.getUuid().toString());
                    stmt.setString(2, product.getUuid().toString());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao reverter transação", ex);
            }
            throw new RuntimeException("Falha ao registrar venda", e);
        }
    }

    @Override
    public Optional<Sales> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Sales> findAll() {
        return new ArrayList<>();
    }

    @Override
    public void deleteById(UUID id) {
    }
}



