package org.example.entities;

import java.util.UUID;

public class User extends Entity {
    private String userName;
    private String email;
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(UUID uuid, String userName, String email, String password) {
        super(uuid);
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return String.format("%s - %s", this.userName, this.email);
    }
}
