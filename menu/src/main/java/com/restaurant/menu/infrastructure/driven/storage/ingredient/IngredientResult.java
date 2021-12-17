package com.restaurant.menu.infrastructure.driven.storage.ingredient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientResult {
    private StockItemResultId id;
    private IngredientResultId ingredient;
    private int amount;
}
