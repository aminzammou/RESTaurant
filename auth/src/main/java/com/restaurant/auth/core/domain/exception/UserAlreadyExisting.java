package com.restaurant.auth.core.domain.exception;

public class UserAlreadyExisting extends RuntimeException {
    public UserAlreadyExisting(String message) {
        super(message);
    }
}