package com.restaurant.menu.domain.repositories;

import com.restaurant.menu.domain.Dish;
import com.restaurant.menu.domain.Ingredient;
import com.restaurant.menu.domain.IngredientId;

import java.util.Optional;

public interface IngredientRepository {
    Optional<Ingredient> findById(int id);

    void save(Ingredient ingredient);


}
