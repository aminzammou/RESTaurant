package com.restaurant.menu.core.port.storage;

import com.restaurant.menu.core.domain.Ingredient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository {
    List<Ingredient> findAll();
    Optional<Ingredient> findById(UUID id);
}
