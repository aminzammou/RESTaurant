package com.restaurant.order.infrasructure.driven.storage.dish;

import com.restaurant.order.core.domain.Dish;
import com.restaurant.order.core.domain.DishID;
import com.restaurant.order.core.ports.storage.DishRepository;
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
    public List<Dish> findAll() {
        return null;
    }

    @Override
    public Optional<Dish> findbyId(UUID id) {
        URI uri = URI.create(this.rootPath + "/menu/dish/" + id);
        DishResult results = this.client.getForObject(uri, DishResult.class);

        if (results == null) {
            return Optional.empty();
        }
        DishID dishId = new DishID(UUID.fromString(results.getDishId().getId()));

        return Optional.of(new Dish(dishId, results.getPrice(), results.getName(), results.isAvailable(), results.getMaxAmount()));


    }
}