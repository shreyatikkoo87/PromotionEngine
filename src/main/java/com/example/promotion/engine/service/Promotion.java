package com.example.promotion.engine.service;

import java.util.Map;

public abstract class Promotion {
    public abstract double applyPromotion(Map<String, Integer> cartItems);
}
