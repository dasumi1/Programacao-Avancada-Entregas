package org.example.entities;

import java.util.UUID;

public class Product extends Entity {
    private final int id;
    private final String name;
    private final double price;

    public Product(UUID uuid, int id, String name, double price) {
        super(uuid);
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String toString() {
        return String.format("%s - %s - %s", this.id, this.name, this.price);
    }
}
