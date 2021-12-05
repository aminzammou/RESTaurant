package com.restaurant.menu.application;

import com.restaurant.menu.domain.repositories.DishRepository;
import com.restaurant.menu.port.adapter.services.DishService;
import com.restaurant.menu.port.adapter.services.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;

public class menuController {
    private final DishService dishService;
    private final IngredientService ingredientService;

    public menuController(DishService dishService, IngredientService ingredientService) {
        this.dishService = dishService;
        this.ingredientService = ingredientService;
    }

//    @GetMapping(path = "/")
//    public MenuDTO getMenu(){
//
//    }
}
