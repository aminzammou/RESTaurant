package com.restaurant.menu.core.application;

import com.restaurant.menu.core.application.query.GetDishById;
import com.restaurant.menu.core.application.query.GetDisheshByOrder;
import com.restaurant.menu.core.domain.Dish;
import com.restaurant.menu.core.domain.DishId;
import com.restaurant.menu.core.domain.Ingredient;
import com.restaurant.menu.core.application.query.ListDishes;
import com.restaurant.menu.core.domain.event.ListIngredients;
import com.restaurant.menu.core.domain.exception.DishNotFound;
import com.restaurant.menu.core.port.storage.DishRepository;
import com.restaurant.menu.core.port.storage.IngredientRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Dish> handle(GetDisheshByOrder query) {
        Optional<Dish> temp;
        List<Dish> dishes = new ArrayList<>();
        List<DishId> dishIds = new ArrayList<>();
        for(DishId dishId :query.getDishesIdByOrder()){
            temp = dishRepository.findByDishId(dishId.getId());
            if(temp.isPresent()) {
                dishes.add(temp.get());
            }else {
                dishIds.add(dishId);
            }
        }
        return dishes;
    }

    public Dish handle(GetDishById query) {
        return this.dishRepository.findByDishId(query.getId())
                .orElseThrow(() -> new DishNotFound(query.getId().toString()));
    }

    public List<Ingredient> handle(ListIngredients query){
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.ingredientRepository.findAll();
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }
}
