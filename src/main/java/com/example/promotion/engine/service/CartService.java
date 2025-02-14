package com.example.promotion.engine.service;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    private Map<String, Integer> cartItems = new HashMap<>();

    public void addItem(String skuId, int quantity) {
        cartItems.put(skuId, cartItems.getOrDefault(skuId, 0) + quantity);
    }

    public Map<String, Integer> getCartItems() {
        return cartItems;
    }

    public void removeItem(String skuId) {
        cartItems.remove(skuId);
    }
}
