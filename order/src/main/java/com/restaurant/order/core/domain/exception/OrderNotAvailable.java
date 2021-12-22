package com.restaurant.order.core.domain.exception;

public class OrderNotAvailable extends RuntimeException {
    public OrderNotAvailable(String message) {
        super(message);
    }
}
