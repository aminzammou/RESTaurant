package com.restaurant.menu.infrastructure.config;

import com.restaurant.menu.infrastructure.driven.storage.ingredient.HttpIngredientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.stock}")
    private String rootPath;

    @Bean
    public HttpIngredientRepository httpIngredientRepository() {
        return new HttpIngredientRepository(rootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
