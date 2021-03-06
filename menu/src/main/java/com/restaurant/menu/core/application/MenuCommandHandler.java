package com.restaurant.menu.core.application;

import com.restaurant.menu.core.application.command.*;
import com.restaurant.menu.core.domain.Dish;
import com.restaurant.menu.core.domain.Ingredient;
import com.restaurant.menu.core.domain.exception.DishNotFound;
import com.restaurant.menu.core.domain.exception.IngredientNotFound;
import com.restaurant.menu.core.port.storage.DishRepository;
import com.restaurant.menu.core.port.storage.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MenuCommandHandler {
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;

    public MenuCommandHandler(DishRepository dishRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Dish handle(CreateDish command) {
        Dish dish = new Dish(command.getName(), command.getCategory(), command.getPrice(), command.isAvailable(), command.getIngredientList());

        List<ChangeDishStatus> ingredients = this.getDishStatuses(dish);
        dish.setMaxAmount(ingredients);

        this.dishRepository.save(dish);

        return dish;
    }

    private List<ChangeDishStatus> getDishStatuses(Dish dish) {
        List<ChangeDishStatus> ingredients = new ArrayList<>();
        if(dish.getIngredients() != null) {
            for (Ingredient ingredient : dish.getIngredients()) {
                Ingredient ingredientFromRepository = ingredientRepository.findById(ingredient.getIngredientId()).orElseThrow(() -> new IngredientNotFound(ingredient.getIngredientId().toString()));
                ingredients.add(new ChangeDishStatus(ingredientFromRepository.getIngredientId(), ingredientFromRepository.getAmount()));
            }
        }
        return ingredients;
    }

    public Dish handle(RenameDish command) {
        Dish dish = this.getDishById(command.getId());

        dish.rename(command.getName());
        this.dishRepository.save(dish);

        return dish;
    }

    public Dish handle(AddIngredient command){
        Dish dish = this.getDishById(command.getId());

        Ingredient ingredient = new Ingredient(ingredientRepository.findById(command.getIngredient().getIngredientId()).orElseThrow(() -> new IngredientNotFound(command.getIngredient().getIngredientId().toString())),command.getIngredient().getAmount());
        dish.addIngredient(ingredient);
        List<ChangeDishStatus> ingredients = this.getDishStatuses(dish);
        dish.setMaxAmount(ingredients);
        this.dishRepository.save(dish);

        return dish;
    }

    public Dish handle(RemoveIngredient command){
        Dish dish = this.getDishById(command.getId());

        dish.removeIngredient(command.getIngredientId());
        if (!dish.getIngredients().isEmpty()) {
            List<ChangeDishStatus> ingredients = this.getDishStatuses(dish);
            dish.setMaxAmount(ingredients);
        }else {
            dish.clearMaxAmount();
        }
        this.dishRepository.save(dish);

        return dish;
    }

    public Dish handle(RemoveDish command) {
        Dish dish = this.getDishById(command.getId());

        this.dishRepository.delete(dish);

        return dish;
    }

    public List<Dish> handle(ChangeDishStatus command) {
        //1. Haal dishes op met ingredient
        List<Dish> dishes = this.getDishByIngredientId(command.getIngredientId());

        //2. Pas voor elke dish zijn status aan
        for (Dish dish: dishes){
//            List<ChangeDishStatus> ingredients = this.getDishStatuses(dish);
            dish.setMaxAmount(List.of(new ChangeDishStatus(command.getIngredientId(), command.getAmount())));
            this.dishRepository.save(dish);
        }

        return dishes;
    }

    private List<Dish> getDishByIngredientId(UUID id) {
        return this.dishRepository.findByIngredientId(id);
//                .orElseThrow(() -> new DishNotFound(id.toString()));
    }

    private Dish getDishById(UUID id) {
        return this.dishRepository.findByDishId(id)
                .orElseThrow(() -> new DishNotFound(id.toString()));
    }
}
