package com.example.promotion.engine.exceptions;

public class PromotionNotApplicableException extends RuntimeException {
    public PromotionNotApplicableException(String message) {
        super(message);
    }
}