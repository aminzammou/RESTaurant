package com.restaurant.stock.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private DishID id;
    private List<DishIngredient> ingredients;
}
