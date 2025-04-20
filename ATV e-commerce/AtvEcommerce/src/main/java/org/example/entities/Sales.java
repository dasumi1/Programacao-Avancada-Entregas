package org.example.entities;

import org.example.paymentMethods.PaymentType;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Sales extends Entity {
    private PaymentType paymentType;
    private Date saleDate;
    private User user;
    private List<Product> products;
    private double totalAmount;

    public Sales(PaymentType paymentType, User user, List<Product> products) {
        this.paymentType = paymentType;
        this.user = user;
        this.products = products;
        this.saleDate = new Date();
        this.totalAmount = calculateTotal();
    }

    private double calculateTotal() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}