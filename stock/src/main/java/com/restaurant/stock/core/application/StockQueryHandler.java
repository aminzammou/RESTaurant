package com.restaurant.stock.core.application;

import com.restaurant.stock.core.application.query.GetAllStockItems;
import com.restaurant.stock.core.application.query.GetStockItemByIngredient;
import com.restaurant.stock.core.domain.IngredientId;
import com.restaurant.stock.core.domain.StockItem;
import com.restaurant.stock.core.domain.exception.StockItemNotFound;
import com.restaurant.stock.core.port.storage.IngredientRepository;
import com.restaurant.stock.core.port.storage.StockItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockQueryHandler {
    private StockItemRepository stockItemRepository;
    private IngredientRepository ingredientRepository;

    public StockQueryHandler(StockItemRepository stockItemRepository, IngredientRepository ingredientRepository) {
        this.stockItemRepository = stockItemRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<StockItem> handle(GetAllStockItems query) {
        return this.stockItemRepository.findAll();
    }

    public StockItem handle(GetStockItemByIngredient query) {
        return this.stockItemRepository.findByIngredient(new IngredientId(query.getIngredientId())).orElseThrow(() -> new StockItemNotFound("Stock item not found"));
    }
}


