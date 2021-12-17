package com.restaurant.order.core.ports.storage;

import com.restaurant.order.core.domain.Dish;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishRepository {
    List<Dish> findAll();
//    @Query("{'_id._id': ?0 }")
    Optional<Dish> findbyId(UUID uuid);
}
