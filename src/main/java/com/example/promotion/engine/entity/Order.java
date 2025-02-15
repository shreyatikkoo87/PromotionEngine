package com.example.promotion.engine.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public void clearItems() {
        items.clear();
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (OrderItem item : this.getItems()) {
            total += (item.getPrice() - item.getDiscount());
        }
        return total;
    }
}

