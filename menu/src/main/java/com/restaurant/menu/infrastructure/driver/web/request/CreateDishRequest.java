package com.restaurant.menu.infrastructure.driver.web.request;

import com.restaurant.menu.core.domain.Category;
import com.restaurant.menu.core.domain.Ingredient;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CreateDishRequest {
    @NotBlank
    public String name;
    @NotBlank
    public Category category;
    @NotBlank
    public double price;
    public List<Ingredient> ingredientList;
}
