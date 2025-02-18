package com.example.promotion;


import com.example.promotion.engine.entity.Order;
import com.example.promotion.engine.entity.OrderItem;
import com.example.promotion.engine.entity.Product;
import com.example.promotion.engine.service.BuyNItemsForFixedPricePromotion;
import com.example.promotion.engine.service.IPromotion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyNItemsForFixedPricePromotionTest {

    // Create products with their prices
    final Product productA = new Product("A", 50.0);
    final Product productB = new Product("B", 30.0);
    final Product productC = new Product("C", 20.0);

    // Promotion to apply: "3 A's for 130"
    IPromotion promotion = new BuyNItemsForFixedPricePromotion("A", 3, 130.0);

    @Test
    public void testSingleProductA() {
        // Scenario 1: Buy 1 A (No promotion applicable)
        Order order = new Order();
        order.addItem(new OrderItem(productA, 1)); // Buying 1 A
        promotion.apply(order); // Apply the promotion
        double expected = 50.0; // No promotion applied, price is 50
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testTwoProductA() {
        // Scenario 2: Buy 2 A's (No promotion applicable)
        Order order = new Order();
        order.addItem(new OrderItem(productA, 2)); // Buying 2 A's
        promotion.apply(order); // Apply the promotion
        double expected = 2 * 50.0; // No promotion applied, total price is 100
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testThreeProductA() {
        // Scenario 3: Buy 3 A's (Promotion applied)
        Order order = new Order();
        order.addItem(new OrderItem(productA, 3)); // Buying 3 A's
        promotion.apply(order); // Apply the promotion
        double expected = 130.0; // Promotion applies: 130 for 3 A's
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testFourProductA() {
        // Scenario 4: Buy 4 A's (Promotion applied for 3 A's and remaining 1 A at full price)
        Order order = new Order();
        order.addItem(new OrderItem(productA, 4)); // Buying 4 A's
        promotion.apply(order); // Apply the promotion
        double expected = 130.0 + 50.0; // 130 for 3 A's and 50 for remaining 1 A
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testFiveProductA() {
        // Scenario 5: Buy 5 A's (Promotion applies for 3 A's and 2 A's at full price)
        Order order = new Order();
        order.addItem(new OrderItem(productA, 5)); // Buying 5 A's
        promotion.apply(order); // Apply the promotion
        double expected = 130.0 + (2 * 50.0); // 130 for 3 A's and 100 for remaining 2 A's
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testProductAAndB() {
        // Scenario 6: Buy 1 A and 1 B (No promotion applied)
        Order order = new Order();
        order.addItem(new OrderItem(productA, 1)); // Buying 1 A
        order.addItem(new OrderItem(productB, 1)); // Buying 1 B
        promotion.apply(order); // Apply the promotion
        double expected = 50.0 + 30.0; // No promotion applies to A or B, total price is 80
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testMultipleProductsDifferentQuantities() {
        // Scenario 7: Buy 4 A's, 3 B's, and 2 C's (Apply promotions where applicable)
        Order order = new Order();
        order.addItem(new OrderItem(productA, 4)); // 4 A's
        order.addItem(new OrderItem(productB, 3)); // 3 B's
        order.addItem(new OrderItem(productC, 2)); // 2 C's
        promotion.apply(order); // Apply the promotion for A's

        // Promotion applies for 3 A's for 130 and 1 A at full price
        double expected = 130.0 + 50.0 + (3 * 30.0) + (2 * 20.0); // 130 + 50 + 90 + 40 = 310
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }
}
