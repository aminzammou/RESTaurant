package com.restaurant.menu.domain.repositories;

import com.restaurant.menu.domain.Dish;

import java.util.Optional;

public interface DishRepository {
    Optional<Dish> findById(int id);

    void save(Dish dish);

}
