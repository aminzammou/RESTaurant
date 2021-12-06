package com.restaurant.menu.core.application;

import com.restaurant.menu.core.port.storage.DishRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuCommandHandler {
    private final DishRepository dishRepository;

    public MenuCommandHandler(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
}
