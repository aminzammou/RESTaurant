package com.restaurant.stock.infrastructure.driven.storage;

import lombok.Data;

import java.util.UUID;

@Data
public class DishResultIngredient {
    private UUID ingredientId;
    private int amount;
}
