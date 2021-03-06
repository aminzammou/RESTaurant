package com.restaurant.stock.infrastructure.driver.messaging.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLine {
    private int amount;
    private Dish dish;

    public OrderLine(Dish dish, int amount) {
        this.dish = dish;
        this.amount = amount;
    }
}