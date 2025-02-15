package com.example.promotion.engine.entity;

public class OrderItem {
    private Product product;
    private int quantity;
    private double price;
    private double discount = 0.0;

    private boolean isPromotionApplied = false;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return quantity * this.product.getPrice();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isPromotionApplied() {
        return isPromotionApplied;
    }

    public void setPromotionApplied(boolean promotionApplied) {
        isPromotionApplied = promotionApplied;
    }
}
