package com.restaurant.order.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class Dish {
    private DishID id;
    private Double dishPrice;
    private String dishName;

    public Dish(DishID id, Double dishPrice, String dishName) {
        this.id = id;
        this.dishPrice = dishPrice;
        this.dishName = dishName;
    }
}
