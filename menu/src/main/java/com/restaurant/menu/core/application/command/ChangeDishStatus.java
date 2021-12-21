package com.restaurant.menu.core.application.command;

import com.restaurant.menu.core.domain.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ChangeDishStatus {
    private UUID ingredientId;
    private int amount;
}
