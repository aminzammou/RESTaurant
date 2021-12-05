package com.restaurant.menu.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Ingredient {
    private IngredientId ingredientId;
    private String name;
    private Date expirationDate;


}
