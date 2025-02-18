package com.example.promotion;


import com.example.promotion.engine.entity.Order;
import com.example.promotion.engine.entity.OrderItem;
import com.example.promotion.engine.entity.Product;
import com.example.promotion.engine.service.BuyTwoItemsForFixedPricePromotion;
import com.example.promotion.engine.service.IPromotion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTwoItemsForFixedPricePromotionTest {
    // Sample products
    Product productC = new Product("C", 20.0);
    Product productD = new Product("D", 15.0);

    IPromotion promotion = new BuyTwoItemsForFixedPricePromotion("C", "D", 30.0);

    @Test
    public void testBuyCAndDForFixedPriceOnePairInCart() {
        // Scenario: 1 C and 1 D
        Order order = new Order();
        order.addItem(new OrderItem(productC, 1));  // 1 C
        order.addItem(new OrderItem(productD, 1));  // 1 D
        promotion.apply(order);  // Apply promotion
        double expected = 30.0;  // Fixed price for 1 C + 1 D
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyCAndDForFixedPriceMultiplePairsInCart() {
        // Scenario: 2 C's and 2 D's
        Order order = new Order();
        order.addItem(new OrderItem(productC, 2));  // 2 C's
        order.addItem(new OrderItem(productD, 2));  // 2 D's
        promotion.apply(order);  // Apply promotion
        double expected = 60.0;  // 2 pairs, each at 30
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyCAndDForFixedPriceOddQuantityOfC() {
        // Scenario: 3 C's and 2 D's
        Order order = new Order();
        order.addItem(new OrderItem(productC, 3));  // 3 C's
        order.addItem(new OrderItem(productD, 2));  // 2 D's
        promotion.apply(order);  // Apply promotion
        double expected = 80.0;  // 2 pairs at fixed price + 1 C at regular price
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyCAndDForFixedPriceOddQuantityOfD() {
        // Scenario: 2 C's and 3 D's
        Order order = new Order();
        order.addItem(new OrderItem(productC, 2));  // 2 C's
        order.addItem(new OrderItem(productD, 3));  // 3 D's
        promotion.apply(order);  // Apply promotion
        double expected = 75.0;  // 2 pairs at fixed price + 1 D at regular price
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }


    @Test
    public void testBuyCAndDForFixedPriceNoItems() {
        // Scenario: No items in the cart
        Order order = new Order();  // No items
        promotion.apply(order);  // Apply promotion
        double expected = 0.0;  // Total price should be 0
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyCAndDForFixedPriceMultiplePairsMixedQuantities() {
        // Scenario: 3 C's and 4 D's
        Order order = new Order();
        order.addItem(new OrderItem(productC, 3));  // 3 C's
        order.addItem(new OrderItem(productD, 4));  // 4 D's
        promotion.apply(order);  // Apply promotion
        double expected = 105;  // 3 pairs at 30 each and 1 pair of D at actual price
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyOnlyOneC() {
        // Scenario: Only 1 C
        Order order = new Order();
        order.addItem(new OrderItem(productC, 1));  // 1 C
        promotion.apply(order);  // Apply promotion
        double expected = 20.0;  // Only 1 C at regular price
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyOnlyOneD() {
        // Scenario: Only 1 D
        Order order = new Order();
        order.addItem(new OrderItem(productD, 1));  // 1 D
        promotion.apply(order);  // Apply promotion
        double expected = 15.0;  // Only 1 D at regular price
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyOneCAndOneD() {
        // Scenario: 1 C and 1 D
        Order order = new Order();
        order.addItem(new OrderItem(productC, 1));  // 1 C
        order.addItem(new OrderItem(productD, 1));  // 1 D
        promotion.apply(order);  // Apply promotion
        double expected = 30.0;  // Fixed price for 1 C + 1 D
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyOneCAndTwoD() {
        // Scenario: 1 C and 2 D's
        Order order = new Order();
        order.addItem(new OrderItem(productC, 1));  // 1 C
        order.addItem(new OrderItem(productD, 2));  // 2 D's
        promotion.apply(order);  // Apply promotion
        double expected = 45.0;  // 1 pair at fixed price + 1 D at regular price
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyTwoCAndOneD() {
        // Scenario: 2 C's and 1 D
        Order order = new Order();
        order.addItem(new OrderItem(productC, 2));  // 2 C's
        order.addItem(new OrderItem(productD, 1));  // 1 D
        promotion.apply(order);  // Apply promotion
        double expected = 50.0;  // 1C and 1D make a pair 30. 1 more C 20
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyTwoC() {
        // Scenario: 2 C's only
        Order order = new Order();
        order.addItem(new OrderItem(productC, 2));  // 2 C's
        promotion.apply(order);  // Apply promotion
        double expected = 40.0;  // 2 C's at regular price
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyTwoD() {
        // Scenario: 2 D's only
        Order order = new Order();
        order.addItem(new OrderItem(productD, 2));  // 2 D's
        promotion.apply(order);  // Apply promotion
        double expected = 30.0;  // 2 D's at regular price
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

}
