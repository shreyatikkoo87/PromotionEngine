package com.example.promotion.engine.service;

import com.example.promotion.engine.entity.Order;
import com.example.promotion.engine.entity.OrderItem;


public class BuyTwoItemsForFixedPricePromotion implements IPromotion {

    private final String product1;
    private final String product2;
    private final double fixedPrice;

    public BuyTwoItemsForFixedPricePromotion(String product1, String product2, double fixedPrice) {
        this.product1 = product1;
        this.product2 = product2;
        this.fixedPrice = fixedPrice;
    }

    /**
     * API to verify the promotion applicable to specific order item
     * Todo provide the implementation
     * @param order
     * @return
     */
    @Override
    public boolean isApplicable(Order order) {
        int item1Count = 0;
        int item2Count = 0;
        for (OrderItem item : order.getItems()) {
            if (item.getProduct().getId().equals(product1) && !item.isPromotionApplied()) {
                item1Count += item.getQuantity();
            } else if (item.getProduct().getId().equals(product2) && !item.isPromotionApplied()) {
                item2Count += item.getQuantity();
            }
        }
        return item1Count >= 1 && item2Count >= 1;
    }

    /**
     * API to apply the promotion to order item.
     * @param order
     * @return
     */
    @Override
    public void apply(Order order) {
        if (isApplicable(order)) {
            int item1Count = order.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(product1))
                    .findFirst().get().getQuantity();

            int item2Count = order.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(product2))
                    .findFirst().get().getQuantity();


            int minCount = Math.min(item1Count, item2Count);
            double regularPrice = minCount * order.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(product1))
                    .findFirst().get().getProduct().getPrice()
                    + minCount * order.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(product2))
                    .findFirst().get().getProduct().getPrice();
            double totalDiscount =  regularPrice - (minCount * fixedPrice);
            for (OrderItem item : order.getItems()) {
                if (item.getProduct().getId().equals(product1) ||
                        item.getProduct().getId().equals(product2)) {
                    if (!item.isPromotionApplied()) {
                        item.setDiscount(totalDiscount / 2);
                        item.setPromotionApplied(true);
                    }
                }
            }
        }

    }
}
