package com.restaurant.menu.core.domain.exception;

public class DishNotFound extends RuntimeException {
    public DishNotFound(String message) {
        super(message);
    }
}
