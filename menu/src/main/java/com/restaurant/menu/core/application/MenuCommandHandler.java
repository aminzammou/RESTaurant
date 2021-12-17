package com.restaurant.menu.core.application;

import com.restaurant.menu.core.application.command.AddIngredient;
import com.restaurant.menu.core.application.command.CreateDish;
import com.restaurant.menu.core.application.command.RemoveIngredient;
import com.restaurant.menu.core.application.command.RenameDish;
import com.restaurant.menu.core.domain.Dish;
import com.restaurant.menu.core.domain.exception.DishNotFound;
import com.restaurant.menu.core.port.storage.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuCommandHandler {
    private final DishRepository dishRepository;

    public MenuCommandHandler(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish handle(CreateDish command) {
        Dish dish = new Dish(command.getName(), command.getCategory(), command.getPrice(), command.getIngredientList());

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

        dish.addIngredient(command.getIngredient());
        this.dishRepository.save(dish);

        return dish;
    }

    public Dish handle(RemoveIngredient command){
        Dish dish = this.getDishById(command.getId());

        dish.removeIngredient(command.getIngredient());
        this.dishRepository.save(dish);

        return dish;
    }

    private Dish getDishById(UUID id) {
        return this.dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFound(id.toString()));
    }


}
