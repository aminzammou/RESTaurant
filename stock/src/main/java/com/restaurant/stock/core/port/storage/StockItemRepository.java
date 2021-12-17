package com.restaurant.stock.core.port.storage;

import com.restaurant.stock.core.domain.IngredientId;
import com.restaurant.stock.core.domain.StockItem;
import com.restaurant.stock.core.domain.StockItemId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface StockItemRepository extends MongoRepository<StockItem, StockItemId> {
    @Query("{ingredient: ?0}")
    Optional<StockItem> findByIngredient(IngredientId ingredientId);
}
