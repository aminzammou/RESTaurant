package com.restaurant.stock.core.domain;

import com.restaurant.stock.core.domain.exception.StockLessThenZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockItem {
    @Id
    private StockItemId id;
    @Indexed(unique = true)
    private IngredientId ingredient;
    private int amount;

    public StockItem(IngredientId ingredient, int amount) {
        this.id = new StockItemId(UUID.randomUUID());
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public void increaseStock(int amount) {
        this.amount += amount;
    }

    public void decreaseStock(int amount) throws StockLessThenZero {
        if(this.amount - amount < 0) {
            throw new StockLessThenZero("Stock item amount will be less than zero");
        }else if(this.amount - amount == 0) {
            // create StockItemEmpty event
        }
        this.amount -= amount;
    }
}
