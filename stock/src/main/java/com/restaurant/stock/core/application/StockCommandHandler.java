package com.restaurant.stock.core.application;

import com.restaurant.stock.core.application.command.AddIngredient;
import com.restaurant.stock.core.application.command.RemoveIngredient;
import com.restaurant.stock.core.domain.Ingredient;
import com.restaurant.stock.core.domain.StockItem;
import com.restaurant.stock.core.domain.exception.StockItemNotFound;
import com.restaurant.stock.core.domain.exception.StockLessThenZero;
import com.restaurant.stock.core.port.storage.IngredientRepository;
import com.restaurant.stock.core.port.storage.StockItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StockCommandHandler {
    private StockItemRepository stockItemRepository;
    private IngredientRepository ingredientRepository;

    public StockCommandHandler(StockItemRepository stockItemRepository, IngredientRepository ingredientRepository) {
        this.stockItemRepository = stockItemRepository;
        this.ingredientRepository = ingredientRepository;
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

//    public StockItem handle(AddIngredient command) {
//
//    }
//
//    private StockItem getStockItemById(UUID id) {
//        return this.stockItemRepository.findById(id).orElseThrow(() -> new StockItemNotFound("Stock item not found"));
//    }
}
