package com.example.promotion.engine.service;

import java.util.Map;

public class OrderService {

    private CartService cartService;
    private PromotionService promotionService;

    public OrderService(CartService cartService, PromotionService promotionService) {
        this.cartService = cartService;
        this.promotionService = promotionService;
    }

    public double calculateTotal() {
        Map<String, Integer> cartItems = cartService.getCartItems();
        double promotionsApplied = promotionService.applyPromotions(cartItems);

        double totalPrice = 0;
        // Add the total price of items without promotions
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            totalPrice += entry.getValue() * getUnitPrice(entry.getKey());
        }

        return totalPrice - promotionsApplied;
    }

    private double getUnitPrice(String skuId) {
        // Mock unit price lookup. In a real-world scenario, this would come from a database or configuration.
        switch (skuId) {
            case "A":
                return 50;
            case "B":
                return 30;
            case "C":
                return 20;
            case "D":
                return 15;
            default:
                return 0;
        }
    }
}
