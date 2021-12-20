package com.restaurant.stock.core.port.storage;

import com.restaurant.stock.core.domain.Dish;

import java.util.Optional;
import java.util.UUID;

public interface DishRepository {
    Optional<Dish> findById(UUID uuid);
}
