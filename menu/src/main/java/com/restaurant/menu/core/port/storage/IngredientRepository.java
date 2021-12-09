package com.restaurant.menu.core.port.storage;

import com.restaurant.menu.core.domain.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends MongoRepository<Ingredient, UUID> {


}
