package com.example.promotion.engine.service;

import java.util.List;
import java.util.Map;

public class PromotionService {

    private List<Promotion> activePromotions;

    public PromotionService(List<Promotion> activePromotions) {
        this.activePromotions = activePromotions;
    }

    public double applyPromotions(Map<String, Integer> cartItems) {
        double totalDiscount = 0;
        for (Promotion promotion : activePromotions) {
            totalDiscount += promotion.applyPromotion(cartItems);
        }
        return totalDiscount;
    }
}
