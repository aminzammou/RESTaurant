package com.restaurant.menu.core.port.storage;

import com.restaurant.menu.core.domain.Dish;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishRepository extends MongoRepository<Dish, UUID> {
    @Query("{'_id._id': ?0 }")
    Optional<Dish> findByDishId(UUID id);

//    @Query("{'_id._id': ?0 }")
//    List<Dish> findByIngredientId(UUID id);

    @Query("{ingredient: ?0}")
    List<Dish> findByIngredientId(UUID ingredientId);

}
