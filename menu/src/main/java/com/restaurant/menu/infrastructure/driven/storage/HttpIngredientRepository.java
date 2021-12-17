//package com.restaurant.menu.infrastructure.driven.storage;
//
//import com.restaurant.menu.core.domain.Ingredient;
//import com.restaurant.menu.core.port.storage.IngredientRepository;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Optional;
//
//public class HttpIngredientRepository implements IngredientRepository {
//    private final String rootPath;
//    private final RestTemplate client;
//
//    public HttpIngredientRepository(String rootPath, RestTemplate client) {
//        this.rootPath = rootPath;
//        this.client = client;
//    }
//
//    @Override
//    public Optional<Ingredient> findById(int id) {
//        return Optional.empty();
//    }
//}
