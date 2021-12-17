package com.restaurant.stock.core.port.storage;

import com.restaurant.stock.core.domain.Ingredient;
import com.restaurant.stock.core.domain.IngredientId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends MongoRepository<Ingredient, IngredientId> {
    @Query("{'_id._id': ?0}")
    Optional<Ingredient> findById(UUID id);
    @Query("{'name': ?0}")
    Optional<Ingredient> findByName(String name);

}
