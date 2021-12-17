package com.restaurant.stock.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddIngredient {
    private String name;
    private int amount;
}
