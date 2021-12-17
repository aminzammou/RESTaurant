package com.restaurant.menu.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document
public class Dish {
    @Id
    private DishId dishId;
    private String name;
    private Category category;
    private double price;
    private State state;
    private List<Ingredient> ingredients = new ArrayList<>();

    public Dish(String name, Category category, double price, State state, List<Ingredient> ingredients) {
        this.dishId = new DishId(UUID.randomUUID());
        this.name = name;
        this.category = category;
        this.price = price;
        this.state = state;
        this.ingredients = ingredients;
    }



    public boolean checkForIngredients(ArrayList<Ingredient> ingredients){
        for(Ingredient ingredient: ingredients){

            return false;
        }
        return true;
    }

    public void rename(String name) {
        this.name= name;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientId ingredientId){
//        for(Ingredient ingredient: ingredients){
//            if (ingredient.getIngredientId().getId() == ingredientId.getId()){
//                ingredients.remove(ingredient);
//            }
//        }
        ingredients.removeIf(ingredient -> ingredient.getIngredientId().getId().equals(ingredientId.getId()));
    }
}