package com.restaurant.menu.infrastructure.driver.web.request;

import com.restaurant.menu.core.domain.Ingredient;

import javax.validation.constraints.NotBlank;

public class IngredientRequest {
    @NotBlank
    public Ingredient ingredient;
}
