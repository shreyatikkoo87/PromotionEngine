package com.example.promotion.engine.entity;

public class Product {
    private String id;
    private double price;

    public Product(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return this.id;
    }

    public double getPrice() {
        return this.price;
    }
}
