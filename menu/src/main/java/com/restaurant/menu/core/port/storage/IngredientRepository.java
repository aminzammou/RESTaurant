package com.restaurant.menu.core.port.storage;

import com.restaurant.menu.core.domain.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Optional<Ingredient> findById(int id);


}
