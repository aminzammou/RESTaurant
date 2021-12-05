package com.restaurant.menu.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class Dish {
    private DishId dishId;
    private String name;
    private ArrayList<Ingredient> ingredients;

    public boolean checkForIngredients(ArrayList<Ingredient> ingredients){
        for(Ingredient ingredient: ingredients){
            return false;
        }
        return true;
    }
}
