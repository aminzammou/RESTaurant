package com.restaurant.order.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Dish {
    private DishID id;
    private Double dishPrice;
    private String dishName;
    private boolean isAvailable;
    private int maxAmount;

    public Dish(DishID id, Double dishPrice, String dishName) {
        this.id = id;
        this.dishPrice = dishPrice;
        this.dishName = dishName;
    }

    public Dish(DishID id, Double dishPrice, String dishName, boolean isAvailable, int maxAmount) {
        this.id = id;
        this.dishPrice = dishPrice;
        this.dishName = dishName;
        this.isAvailable = isAvailable;
        this.maxAmount = maxAmount;
    }
}
