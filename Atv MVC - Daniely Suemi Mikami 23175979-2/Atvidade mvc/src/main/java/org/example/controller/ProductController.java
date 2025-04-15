package org.example.controller;

import org.example.model.Product;
import org.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void registerProduct(String name, double price){
        Product product = new Product(name, price);
        productRepository.save(product);
    }

    public Optional<Product> getProductById(UUID id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public boolean removeProduct(UUID id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
