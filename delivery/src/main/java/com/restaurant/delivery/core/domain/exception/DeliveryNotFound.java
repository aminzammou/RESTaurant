package com.restaurant.delivery.core.domain.exception;

public class DeliveryNotFound extends RuntimeException {
    public DeliveryNotFound(String message) {
        super(message);
    }
}
