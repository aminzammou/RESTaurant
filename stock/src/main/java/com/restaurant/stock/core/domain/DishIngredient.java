package com.restaurant.stock.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DishIngredient {
    private UUID ingredientId;
    private int amount;
}
