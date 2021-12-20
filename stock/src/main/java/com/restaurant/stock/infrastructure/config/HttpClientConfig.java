package com.restaurant.stock.infrastructure.config;

import com.restaurant.stock.infrastructure.driven.storage.HttpDishRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.dish}")
    private String dishRootPath;

    @Bean
    public HttpDishRepository httpDishRepository() {
        return new HttpDishRepository(dishRootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
