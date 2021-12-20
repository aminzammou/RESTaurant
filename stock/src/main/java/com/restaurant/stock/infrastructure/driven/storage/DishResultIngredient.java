package com.restaurant.stock.infrastructure.driven.storage;

import lombok.Data;

@Data
public class DishResultIngredient {
    private DishResultIngredientId ingredientId;
    private int amount;
}
