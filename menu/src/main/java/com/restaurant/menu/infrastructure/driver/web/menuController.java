package com.restaurant.menu.infrastructure.driver.web;


import com.restaurant.menu.core.application.command.*;
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
import com.restaurant.menu.infrastructure.driver.web.request.RemoveIngredientRequest;
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

    @GetMapping("/dish/ingredient/{ingredientId}")
    public List<Dish> findDishesByIngredient(@PathVariable UUID ingredientId, @RequestBody int amount) {
        return this.commandHandler.handle(new ChangeDishStatus(ingredientId, amount));
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

    @DeleteMapping("/dish/{id}")
    public Dish removeDish(@PathVariable UUID id) {
        return this.commandHandler.handle(new RemoveDish(id));
    }

    @PostMapping("/dish/{id}/ingredient")
    public Dish addIngredient(@PathVariable UUID id, @Valid @RequestBody IngredientRequest request) {
        return this.commandHandler.handle(new AddIngredient(id, new Ingredient(UUID.fromString(request.id.getId()),request.amount)));
    }

    @DeleteMapping("/dish/{id}/ingredient/{ingredientId}")
    public Dish removeIngredient(@PathVariable UUID id, @PathVariable UUID ingredientId) {
        return this.commandHandler.handle(new RemoveIngredient(id, ingredientId)
        );
    }
}
