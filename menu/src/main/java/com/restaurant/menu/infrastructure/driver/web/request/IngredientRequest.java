package com.restaurant.menu.infrastructure.driver.web.request;

import com.restaurant.menu.core.domain.Ingredient;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class IngredientRequest {
    @NotBlank
    public IngredientIdRequest id;
    @NotBlank
    public int amount;
}
