package org.example.repository;

import org.example.entities.User;

import java.sql.*;
import java.util.*;

public class UserRepository implements EntityRepository<User> {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) {
        String query = "INSERT INTO users VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, user.getUuid().toString());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("Erro ao salvar usuário: " + e.getMessage()); // Debug detalhado
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        String query = "SELECT * FROM users WHERE uuid = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, uuid.toString());
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        UUID.fromString(resultSet.getString("uuid")),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        UUID.fromString(resultSet.getString("uuid")),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                System.out.println("Usuário encontrado: " + user.getEmail()); // Debug
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage()); // Debug
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void deleteById(UUID uuid) {
        String query = "DELETE FROM users WHERE uuid = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, uuid.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
