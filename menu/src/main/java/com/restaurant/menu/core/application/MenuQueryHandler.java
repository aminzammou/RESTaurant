package com.restaurant.menu.core.application;

import com.restaurant.menu.core.application.query.GetDishById;
import com.restaurant.menu.core.domain.Dish;
import com.restaurant.menu.core.domain.Ingredient;
import com.restaurant.menu.core.application.query.ListDishes;
import com.restaurant.menu.core.domain.event.ListIngredients;
import com.restaurant.menu.core.domain.exception.DishNotFound;
import com.restaurant.menu.core.port.storage.DishRepository;
import com.restaurant.menu.core.port.storage.IngredientRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuQueryHandler {
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;

    public MenuQueryHandler(DishRepository dishRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Dish> handle(ListDishes query){
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.dishRepository.findAll(sort);
    }

    public Dish handle(GetDishById query) {
        return this.dishRepository.findByDishId(query.getId())
                .orElseThrow(() -> new DishNotFound(query.getId().toString()));
    }

    public List<Ingredient> handle(ListIngredients query){
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.ingredientRepository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }
}
