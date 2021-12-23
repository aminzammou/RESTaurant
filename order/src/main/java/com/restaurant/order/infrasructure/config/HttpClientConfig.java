package com.restaurant.order.infrasructure.config;

import com.restaurant.order.infrasructure.driven.storage.dish.HttpDishRepository;
import com.restaurant.order.infrasructure.driven.storage.user.HttpUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.menu}")
    private String rootPathMenu;

    @Value("${http-client.root-path.auth}")
    private String rootPathAuth;

    @Bean
    public HttpDishRepository httpDishRepository() {
        return new HttpDishRepository(rootPathMenu, restTemplate());
    }

    @Bean
    public HttpUserRepository httpUserRepository() {
        return new HttpUserRepository(rootPathAuth, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
