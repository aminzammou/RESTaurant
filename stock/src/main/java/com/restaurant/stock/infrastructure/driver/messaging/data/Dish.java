package com.restaurant.stock.infrastructure.driver.messaging.data;

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

    public Dish(DishID id, Double dishPrice, String dishName) {
        this.id = id;
        this.dishPrice = dishPrice;
        this.dishName = dishName;
    }
}
