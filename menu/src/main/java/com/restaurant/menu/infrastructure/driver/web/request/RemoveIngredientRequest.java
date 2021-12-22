package com.restaurant.menu.infrastructure.driver.web.request;


import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class RemoveIngredientRequest {
    @NotBlank
    public UUID id;
}
