package com.restaurant.menu.core.application;

import com.restaurant.menu.core.application.command.*;
import com.restaurant.menu.core.domain.Dish;
import com.restaurant.menu.core.domain.Ingredient;
import com.restaurant.menu.core.domain.exception.DishNotFound;
import com.restaurant.menu.core.domain.exception.IngredientNotFound;
import com.restaurant.menu.core.port.storage.DishRepository;
import com.restaurant.menu.core.port.storage.IngredientRepository;
import org.springframework.stereotype.Service;

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
        Dish dish = new Dish(command.getName(), command.getCategory(), command.getPrice(), command.getState(), command.getIngredientList());

        this.dishRepository.save(dish);

        return dish;
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
        this.dishRepository.save(dish);

        return dish;
    }

    public Dish handle(RemoveIngredient command){
        Dish dish = this.getDishById(command.getId());

        dish.removeIngredient(command.getIngredientId());
        this.dishRepository.save(dish);

        return dish;
    }

    public Dish handle(RemoveDish command) {
        Dish dish = this.getDishById(command.getId());

        this.dishRepository.delete(dish);

        return dish;
    }

    private Dish getDishById(UUID id) {
        return this.dishRepository.findByDishId(id)
                .orElseThrow(() -> new DishNotFound(id.toString()));
    }

}
