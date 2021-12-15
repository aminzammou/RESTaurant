package com.restaurant.stock.infrastructure.driver.web;

import com.restaurant.stock.core.application.StockCommandHandler;
import com.restaurant.stock.core.application.StockQueryHandler;
import com.restaurant.stock.core.application.command.AddIngredient;
import com.restaurant.stock.core.application.command.RemoveIngredient;
import com.restaurant.stock.core.application.query.GetAllStockItems;
import com.restaurant.stock.core.application.query.GetStockItemByIngredient;
import com.restaurant.stock.core.domain.StockItem;
import com.restaurant.stock.infrastructure.driver.web.request.AddIngredientRequest;
import com.restaurant.stock.infrastructure.driver.web.request.RemoveIngredientRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockCommandHandler commandHandler;
    private final StockQueryHandler queryHandler;

    public StockController(StockCommandHandler commandHandler, StockQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping("/add-stock")
    StockItem addIngredient(@Valid @RequestBody AddIngredientRequest request) {
        return this.commandHandler.handle(new AddIngredient(request.getName(), request.getAmount()));
    }

    @PostMapping("/remove-stock")
    StockItem removeIngredient(@Valid @RequestBody RemoveIngredientRequest request) {
        return this.commandHandler.handle(new RemoveIngredient(request.getIngredientId(), request.getAmount()));
    }

    @GetMapping
    List<StockItem> getAll(@RequestParam(required = false) UUID ingredientId) {
        if(ingredientId == null) {
            return this.queryHandler.handle(new GetAllStockItems());
        }

        return List.of(this.queryHandler.handle(new GetStockItemByIngredient(ingredientId)));
    }
}
