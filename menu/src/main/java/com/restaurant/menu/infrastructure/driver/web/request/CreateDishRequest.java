package com.restaurant.menu.infrastructure.driver.web.request;

import com.restaurant.menu.core.domain.Category;
import com.restaurant.menu.core.domain.Ingredient;
import com.restaurant.menu.core.domain.State;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CreateDishRequest {
    @NotBlank
    public String name;
    @NotBlank
    public Category category;
    @NotBlank
    public double price;
    @NotBlank
    public State state;

    public List<Ingredient> ingredientList;
}
