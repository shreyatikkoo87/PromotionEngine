package com.example.promotion.engine.service;

import java.util.Map;

public class BuyNForFixedPricePromotion extends Promotion {

    private String skuId;
    private int quantity;
    private double fixedPrice;

    public BuyNForFixedPricePromotion(String skuId, int quantity, double fixedPrice) {
        this.skuId = skuId;
        this.quantity = quantity;
        this.fixedPrice = fixedPrice;
    }

    @Override
    public double applyPromotion(Map<String, Integer> cartItems) {
        if (cartItems.containsKey(skuId)) {
            int availableQty = cartItems.get(skuId);
            int eligibleGroups = availableQty / quantity;
            return eligibleGroups * fixedPrice + (availableQty % quantity) * getUnitPrice(skuId);
        }
        return 0;
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
