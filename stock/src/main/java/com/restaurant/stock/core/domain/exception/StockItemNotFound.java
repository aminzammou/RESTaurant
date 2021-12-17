package com.restaurant.stock.core.domain.exception;

public class StockItemNotFound extends RuntimeException{
    public StockItemNotFound(String message) {
        super(message);
    }
}
