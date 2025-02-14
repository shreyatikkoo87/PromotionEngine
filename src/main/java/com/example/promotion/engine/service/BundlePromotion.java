package com.example.promotion.engine.service;

import java.util.Map;

public class BundlePromotion extends Promotion {

    private String sku1;
    private String sku2;
    private double fixedPrice;

    public BundlePromotion(String sku1, String sku2, double fixedPrice) {
        this.sku1 = sku1;
        this.sku2 = sku2;
        this.fixedPrice = fixedPrice;
    }

    @Override
    public double applyPromotion(Map<String, Integer> cartItems) {
        if (cartItems.containsKey(sku1) && cartItems.containsKey(sku2)) {
            int availableQty1 = cartItems.get(sku1);
            int availableQty2 = cartItems.get(sku2);
            int eligibleBundles = Math.min(availableQty1, availableQty2);
            return eligibleBundles * fixedPrice + (availableQty1 - eligibleBundles) * getUnitPrice(sku1) + (availableQty2 - eligibleBundles) * getUnitPrice(sku2);
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
