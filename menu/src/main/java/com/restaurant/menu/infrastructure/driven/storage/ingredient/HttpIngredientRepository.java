package com.restaurant.menu.infrastructure.driven.storage.ingredient;

import com.restaurant.menu.core.domain.Ingredient;
import com.restaurant.menu.core.port.storage.IngredientRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HttpIngredientRepository implements IngredientRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpIngredientRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public List<Ingredient> findAll() {
        return null;
    }

    @Override
    public Optional<Ingredient> findById(UUID id) {
        URI uri = URI.create(this.rootPath + "/stock/" + id);

        IngredientResult results = this.client.getForObject(uri, IngredientResult.class);

        if (results == null) {
            return Optional.empty();
        }
        UUID ingredientId = UUID.fromString(results.getIngredient().getId());

        return Optional.of(new Ingredient(ingredientId));


    }
}
