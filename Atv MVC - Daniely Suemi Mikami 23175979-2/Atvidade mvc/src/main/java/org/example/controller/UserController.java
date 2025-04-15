package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    public void registerUser(String name, String email, String password) {
        User user = new User(name, email, password);
        repository.save(user);
    }

    public List<User> listAllUsers() {
        return repository.findAll();
    }

    public Optional <User> findById(UUID id) {
        return repository.findById(id);
    }

    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
