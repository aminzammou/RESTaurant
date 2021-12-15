package com.restaurant.stock.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GetStockItemByIngredient {
    private final UUID ingredientId;
}
