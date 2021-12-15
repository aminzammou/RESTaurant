package com.restaurant.stock.core.domain;

import com.restaurant.stock.core.domain.exception.StockItemNotFound;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Document
public class Inventory {
    @Id
    private InventoryId id;
    private List<StockItemId> stockItems = new ArrayList<>();

    public Inventory(List<StockItemId> stock) {
        this.stockItems = stock;
    }

//    public void increaseStock(IngredientId ingredient, int amount) {
//        //hier repository gebruiken?
//
////        Optional<StockItem> foundStockItem = stockItems.stream().findAny().filter(stockItem -> stockItem.getIngredient().equals(ingredient));
//
////        if(foundStockItem.isPresent()) {
////            StockItem stockItem = foundStockItem.get();
////            stockItem.increaseStock(amount);
////        } else {
////            this.stockItems.add(new StockItem(new StockItemId(UUID.randomUUID()), ingredient, amount));
////        }
//    }
//
//    public void decreaseStock(Ingredient ingredient, int amount) {
////        Optional<StockItem> foundStockItem = stockItems.stream().findAny().filter(stockItem -> stockItem.getIngredient().equals(ingredient));
////
////        if(foundStockItem.isPresent()) {
////            StockItem stockItem = foundStockItem.get();
////            stockItem.decreaseStock(amount);
////        }
//    }
//
//    public void removeIngredient(Ingredient ingredient) {
////        Optional<StockItem> foundStockItem = stockItems.stream().findAny().filter(stockItem -> stockItem.getIngredient().equals(ingredient));
////
////        if(foundStockItem.isPresent()) {
////            StockItem stockItem = foundStockItem.get();
////            this.stockItems.remove(stockItem);
////        }
//    }
}
