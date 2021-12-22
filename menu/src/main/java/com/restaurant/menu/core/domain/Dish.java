package com.restaurant.menu.core.domain;

import com.restaurant.menu.core.application.command.ChangeDishStatus;
import com.restaurant.menu.core.domain.exception.IngredientNotFound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private boolean isAvailable = false;
    private List<Ingredient> ingredients = new ArrayList<>();

    public int getMaxAmount() {
        return maxAmount;
    }

    private int maxAmount;

    public Dish(String name, Category category, double price, boolean isAvailable, List<Ingredient> ingredients) {
        this.dishId = new DishId(UUID.randomUUID());
        this.name = name;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
        this.ingredients = ingredients;
    }

    public Dish(String name, Category category, double price, boolean isAvailable, List<Ingredient> ingredients, int maxAmount) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
        this.ingredients = ingredients;
        this.maxAmount = maxAmount;
    }

    public void checkForIngredients(){
//        Ingredient ingredient = ingredients.get(ingredients.indexOf(ingredientId)+1);
        this.isAvailable = maxAmount > 0;
    }

    public void rename(String name) {
        this.name= name;
    }

    public void addIngredient(Ingredient newIngredient){
        //                oldIngredient.setAmount(newIngredient.getAmount());
        ingredients.removeIf(oldIngredient -> oldIngredient.getIngredientId().equals(newIngredient.getIngredientId()));
        this.ingredients.add(newIngredient);
    }

    public void calculateMaxAmount(List<ChangeDishStatus> dishIngredients) {
        List<Integer> max = new ArrayList<>();
        for (ChangeDishStatus ingredient: dishIngredients){
            Ingredient ingredient1 = this.ingredients.stream().filter(ing -> ing.getIngredientId().compareTo(ingredient.getIngredientId()) == 0).findAny().orElseThrow(() -> new IngredientNotFound("Ingredient not found"));

            int amountInStock = ingredient.getAmount();

            int maxWithIngredient = amountInStock/ingredient1.getAmount();

            max.add(maxWithIngredient);
        }
        maxAmount = Collections.min(max);
        checkForIngredients();
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void removeIngredient(UUID ingredientId){
//        for(Ingredient ingredient: ingredients){
//            if (ingredient.getIngredientId().getId() == ingredientId.getId()){
//                ingredients.remove(ingredient);
//            }
//        }
        ingredients.removeIf(ingredient -> ingredient.getIngredientId().equals(ingredientId));
    }
}
