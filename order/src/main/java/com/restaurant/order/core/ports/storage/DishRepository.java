package com.restaurant.order.core.ports.storage;

import com.restaurant.order.core.domain.Dish;
import com.restaurant.order.infrasructure.driven.storage.dish.exception.MenuNotAvailable;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishRepository {
    List<Dish> findAll();
    Optional<Dish> findbyId(UUID uuid) throws MenuNotAvailable;
}
