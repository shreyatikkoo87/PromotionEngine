package com.example.promotion.engine.controller;

import com.example.promotion.engine.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/order")
public class OrderController {

    private CartService cartService;
    private PromotionService promotionService;
    private OrderService orderService;

    public OrderController() {
        this.cartService = new CartService();
        this.promotionService = new PromotionService(Arrays.asList(
                new BuyNForFixedPricePromotion("A", 3, 130),
                new BuyNForFixedPricePromotion("B", 2, 45),
                new BundlePromotion("C", "D", 30)
        ));
        this.orderService = new OrderService(cartService, promotionService);
    }

    @PostMapping("/addItem")
    public void addItem(@RequestParam String skuId, @RequestParam int quantity) {
        cartService.addItem(skuId, quantity);
    }

    @GetMapping("/total")
    public double calculateTotal() {
        return orderService.calculateTotal();
    }
}
