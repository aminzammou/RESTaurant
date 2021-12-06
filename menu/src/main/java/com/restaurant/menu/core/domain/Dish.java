package com.restaurant.menu.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Document
public class Dish {
    @Id
    private DishId dishId;
    private Category category;
    private double price;
    private String name;
    private ArrayList<Ingredient> ingredients;

    public boolean checkForIngredients(ArrayList<Ingredient> ingredients){
        for(Ingredient ingredient: ingredients){

            return false;
        }
        return true;
    }
}
