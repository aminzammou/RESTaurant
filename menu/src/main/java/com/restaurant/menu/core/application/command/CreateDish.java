package com.restaurant.menu.core.application.command;

import com.restaurant.menu.core.domain.Category;
import com.restaurant.menu.core.domain.Ingredient;
import com.restaurant.menu.core.domain.IngredientId;
import com.restaurant.menu.core.domain.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateDish {
    private String name;
    private Category category;
    private double price;
    private State state;
    private List<Ingredient> ingredientList;

}
