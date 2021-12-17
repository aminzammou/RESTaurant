package com.restaurant.stock.core.domain.exception;

public class StockLessThenZero extends Exception{
    public StockLessThenZero(String message) {
        super(message);
    }
}
