package com.restaurant.menu.infrastructure.driver.web;


import com.restaurant.menu.core.application.command.AddIngredient;
import com.restaurant.menu.core.application.command.CreateDish;
import com.restaurant.menu.core.application.command.RemoveIngredient;
import com.restaurant.menu.core.application.command.RenameDish;
import com.restaurant.menu.core.application.query.GetDishById;
import com.restaurant.menu.core.application.query.GetDisheshByOrder;
import com.restaurant.menu.core.domain.Dish;
import com.restaurant.menu.core.application.*;
import com.restaurant.menu.core.domain.DishId;
import com.restaurant.menu.core.domain.Ingredient;
import com.restaurant.menu.core.application.query.ListDishes;
import com.restaurant.menu.core.domain.event.ListIngredients;
import com.restaurant.menu.infrastructure.driver.web.request.CreateDishRequest;
import com.restaurant.menu.infrastructure.driver.web.request.IngredientRequest;
import com.restaurant.menu.infrastructure.driver.web.request.RenameDishRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/dish/{id}")
    public Dish findDishById(@PathVariable String id) {
        return this.queryHandler.handle(new GetDishById(UUID.fromString(id)));
    }

    @GetMapping("/dish/order")
    public List<Dish> findDishesByOrder(@RequestBody List<DishId> listId) {
        return this.queryHandler.handle(new GetDisheshByOrder(listId));
    }

    @GetMapping("/ingredients")
    public List<Ingredient> findIngredients(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new ListIngredients(orderBy, direction));
    }

    @PostMapping("/dish")
    public Dish createDish(@Valid @RequestBody CreateDishRequest request) {
        return this.commandHandler.handle(new CreateDish(request.name, request.category, request.price, request.state, request.ingredientList)
        );
    }

    @PostMapping("/dish/{id}/rename")
    public Dish renameDish(@PathVariable UUID id, @Valid @RequestBody RenameDishRequest request) {
        return this.commandHandler.handle(new RenameDish(id, request.name));
    }



    @PostMapping("/dish/{id}/ingredient")
    public Dish addIngredient(@PathVariable UUID id, @Valid @RequestBody IngredientRequest request) {
        return this.commandHandler.handle(new AddIngredient(id, request.ingredient));
    }

    @DeleteMapping("/dish/{id}/ingredient")
    public Dish removeIngredient(@PathVariable UUID id, @Valid @RequestBody IngredientRequest request) {
        return this.commandHandler.handle(new RemoveIngredient(id, request.ingredient)
        );
    }
}
