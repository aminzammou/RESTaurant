package com.restaurant.stock.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DecreasedStockEvent extends StockEvent{
    private UUID ingredientId;
    private int oldAmount;
    private int newAmount;

    @Override
    public String getEventKey() {
        return "stock.updated.decreased";
    }
}
