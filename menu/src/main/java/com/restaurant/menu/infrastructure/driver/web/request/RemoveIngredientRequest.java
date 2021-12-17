package com.restaurant.menu.infrastructure.driver.web.request;

import com.restaurant.menu.core.domain.IngredientId;

import javax.validation.constraints.NotBlank;

public class RemoveIngredientRequest {
    @NotBlank
    public IngredientId id;
}
