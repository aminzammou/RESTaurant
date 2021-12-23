package com.restaurant.stock.infrastructure.driven.storage;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DishResult {
    private DishResultId dishId;
    private List<DishResultIngredient> ingredients;
}
