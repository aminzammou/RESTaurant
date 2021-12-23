package com.restaurant.stock.infrastructure.driven.storage;

import com.restaurant.stock.core.domain.Dish;
import com.restaurant.stock.core.domain.DishID;
import com.restaurant.stock.core.domain.DishIngredient;
import com.restaurant.stock.core.port.storage.DishRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class HttpDishRepository implements DishRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpDishRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public Optional<Dish> findById(UUID id) {
        URI uri = URI.create(this.rootPath + "/menu/dish/" + id);
        DishResult results = this.client.getForObject(uri, DishResult.class);

        if (results == null) {
            return Optional.empty();
        }
        DishID dishId = new DishID(UUID.fromString(results.getDishId().getId()));

        return Optional.of(new Dish(dishId, results.getIngredients().stream().map(resultIngredient -> new DishIngredient(resultIngredient.getIngredientId(), resultIngredient.getAmount())).collect(Collectors.toList())));
    }
}