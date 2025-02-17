package com.example.promotion.engine.service;

import com.example.promotion.engine.entity.Order;
import com.example.promotion.engine.entity.OrderItem;
import com.example.promotion.engine.service.IPromotion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class BuyNItemsForFixedPricePromotion implements IPromotion {

    private String productId;

    private int minQuantity;
    private double fixedPrice;

    public BuyNItemsForFixedPricePromotion(String productId, int minQuantity, double fixedPrice) {
        this.productId = productId;
        this.minQuantity = minQuantity;
        this.fixedPrice = fixedPrice;
    }

    /**
     * Check if the promotion is applicable to the order.
     *
     * @param order the order object.
     * @return true if applicable, false otherwise.
     */
    @Override
    public boolean isApplicable(Order order) {
        // Check if the order contains the required product with enough quantity
        for (OrderItem item : order.getItems()) {
            if (item.getProduct().getId().equals(productId) && item.getQuantity() >= minQuantity) {
                return true; // Promotion is applicable if the product is found and quantity is sufficient
            }
        }
        return false; // Promotion is not applicable
    }

    /**
     * Apply the promotion to the order.
     *
     * @param order the order object to apply the promotion to.
     */
    @Override
    public void apply(Order order) {
        for (OrderItem item : order.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                int quotient = item.getQuantity() / minQuantity;
                int remainder = item.getQuantity() % minQuantity;
                double discount = item.getPrice() -
                        ((quotient * fixedPrice) + (remainder * item.getProduct().getPrice()));
                if (!item.isPromotionApplied()) {
                    item.setDiscount(discount);
                    item.setPromotionApplied(true);
                }
            }
        }
    }
}

