package com.restaurant.menu.infrastructure.driver.web.request;

import com.restaurant.menu.core.domain.Ingredient;
import com.restaurant.menu.core.domain.IngredientId;

import javax.validation.constraints.NotBlank;

public class IngredientRequest {
    @NotBlank
    public Ingredient ingredient;
}
