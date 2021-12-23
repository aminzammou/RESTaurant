package com.restaurant.stock.core.domain;

import com.restaurant.stock.core.domain.event.DecreasedStockEvent;
import com.restaurant.stock.core.domain.event.IncreasedStockEvent;
import com.restaurant.stock.core.domain.event.StockEvent;
import com.restaurant.stock.core.domain.exception.StockLessThenZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;
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
    @Transient
    private List<StockEvent> events = new ArrayList<>();

    public StockItem(IngredientId ingredient, int amount) {
        this.id = new StockItemId(UUID.randomUUID());
        this.ingredient = ingredient;
        this.amount = amount;
        this.events.add(new IncreasedStockEvent(this.id.getId(), 0, amount));
    }

    public void increaseStock(int amount) {
        this.events.add(new IncreasedStockEvent(this.ingredient.getId(), this.amount, this.amount + amount));
        this.amount += amount;
    }

    public void decreaseStock(int amount) throws StockLessThenZero {
        if(this.amount - amount < 0) {
            throw new StockLessThenZero("Stock item amount will be less than zero");
        }
        this.events.add(new DecreasedStockEvent(this.ingredient.getId(), this.amount, this.amount - amount));
        this.amount -= amount;
    }

    public void clearEvents() {
        this.events.clear();
    }
    public List<StockEvent> listEvents() {
        return events;
    }
}
