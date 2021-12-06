package com.restaurant.menu.core.port.storage;

import com.restaurant.menu.core.domain.Dish;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishRepository extends MongoRepository<Dish, UUID> {

}
