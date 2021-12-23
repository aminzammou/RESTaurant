package com.restaurant.stock.core.application;

import com.restaurant.stock.core.application.command.AddIngredient;
import com.restaurant.stock.core.application.command.DecreaseStockByDish;
import com.restaurant.stock.core.application.command.RemoveIngredient;
import com.restaurant.stock.core.domain.*;
import com.restaurant.stock.core.domain.event.StockEvent;
import com.restaurant.stock.core.domain.exception.StockItemNotFound;
import com.restaurant.stock.core.domain.exception.StockLessThenZero;
import com.restaurant.stock.core.port.messaging.StockEventPublisher;
import com.restaurant.stock.core.port.storage.DishRepository;
import com.restaurant.stock.core.port.storage.IngredientRepository;
import com.restaurant.stock.core.port.storage.StockItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockCommandHandler {
    private StockItemRepository stockItemRepository;
    private IngredientRepository ingredientRepository;
    private DishRepository dishRepository;
    private StockEventPublisher eventPublisher;

    public StockCommandHandler(StockItemRepository stockItemRepository, IngredientRepository ingredientRepository, DishRepository dishRepository, StockEventPublisher eventPublisher) {
        this.stockItemRepository = stockItemRepository;
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishRepository;
        this.eventPublisher = eventPublisher;
    }

    public StockItem handle(AddIngredient command) {
        Optional<Ingredient> foundIngredient = this.ingredientRepository.findByName(command.getName());

        Ingredient ingredient;
        if(foundIngredient.isPresent()) {
            ingredient = foundIngredient.get();
        }else {
            ingredient = new Ingredient(command.getName());
            this.ingredientRepository.save(ingredient);
        }
        return increaseStock(command, ingredient);
    }

    public void handle(DecreaseStockByDish command) throws Exception {
        Optional<Dish> foundDish = this.dishRepository.findById(command.getDishId());
        if(foundDish.isEmpty()) {
            // HANDLE NO DISH FOUND
            throw new Exception("Dish not found");
        }
        Dish dish = foundDish.get();
        for(int i = 0; i < dish.getIngredients().size(); i++) {
            DishIngredient dishIngredient = dish.getIngredients().get(i);
            StockItem stockItem = this.stockItemRepository.findByIngredient(new IngredientId(dishIngredient.getIngredientId())).orElseThrow();
            stockItem.decreaseStock(dishIngredient.getAmount());
            this.publishEvents(stockItem);
            this.stockItemRepository.save(stockItem);
        }

    }

    public StockItem handle(RemoveIngredient command) throws StockLessThenZero {
        Optional<StockItem> foundStockItem = this.getStockItemByIngredient(command.getIngredientId());
        if(foundStockItem.isPresent()) {
            StockItem stockItem = foundStockItem.get();
            stockItem.decreaseStock(command.getAmount());
            return this.stockItemRepository.save(stockItem);
        }
        throw new StockItemNotFound("Stock item is not found");
    }

    private Optional<StockItem> getStockItemByIngredient(UUID ingredientId) {
        Optional<Ingredient> foundIngredient = this.ingredientRepository.findById(ingredientId);
        return foundIngredient.flatMap(ingredient -> this.stockItemRepository.findByIngredient(ingredient.getId()));
    }

    private StockItem increaseStock(AddIngredient command, Ingredient ingredient) {
        Optional<StockItem> foundStockItem = this.stockItemRepository.findByIngredient(ingredient.getId());
        StockItem stockItem;
        if(foundStockItem.isPresent()) {
            stockItem = foundStockItem.get();
            stockItem.increaseStock(command.getAmount());
        }else {
            stockItem = new StockItem(ingredient.getId(), command.getAmount());
        }
        return this.stockItemRepository.save(stockItem);
    }

    private StockItem publishEvents(StockItem stockItem) {
        List<StockEvent> events = stockItem.listEvents();
        events.forEach(eventPublisher::publish);
        stockItem.clearEvents();
        return stockItem;
    }

//    public StockItem handle(AddIngredient command) {
//
//    }
//
//    private StockItem getStockItemById(UUID id) {
//        return this.stockItemRepository.findById(id).orElseThrow(() -> new StockItemNotFound("Stock item not found"));
//    }
}
