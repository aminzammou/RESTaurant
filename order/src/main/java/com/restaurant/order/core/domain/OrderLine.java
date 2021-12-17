package com.restaurant.order.core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLine {
    private int amount;
    private Dish dish;
    private Double totalPrice;

    public OrderLine(Dish dish, int amount) {
        this.dish = dish;
        this.amount = amount;
        this.totalPrice = amount * dish.getDishPrice();
    }
}
