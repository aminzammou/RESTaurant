package com.restaurant.order.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLine {
    private Item item;
    private Double totalPrice;

    public OrderLine(Dish dish, int amount) {
        this.dish = dish;
        this.amount = amount;
        this.totalPrice = amount * dish.getDishPrice();
    }
}
