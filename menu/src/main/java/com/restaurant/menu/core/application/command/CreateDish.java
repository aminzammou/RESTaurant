package com.restaurant.menu.core.application.command;

import com.restaurant.menu.core.domain.Category;
import com.restaurant.menu.core.domain.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateDish {
    private String name;
    private Category category;
    private double price;
    private boolean isAvailable;
    private List<Ingredient> ingredientList;

}
