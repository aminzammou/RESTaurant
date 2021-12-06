package com.restaurant.menu.infrastructure.driver.web;


import com.restaurant.menu.core.domain.Dish;
import com.restaurant.menu.core.application.*;
import com.restaurant.menu.core.domain.event.ListDishes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class menuController {
    private final MenuCommandHandler commandHandler;
    private final MenuQueryHandler queryHandler;

    public menuController(MenuCommandHandler commandHandler,MenuQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @GetMapping("/dishes")
    public List<Dish> findDishes(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new ListDishes(orderBy, direction));
    }
}
