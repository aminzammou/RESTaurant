package com.restaurant.menu.infrastructure.driver.web.request;

import com.restaurant.menu.core.application.command.ChangeDishStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class ingredientTest {
    @NotBlank
    public int amount;
}
