package com.restaurant.authentication.core.domain.exception;

public class UserAlreadyExisting extends RuntimeException {
    public UserAlreadyExisting(String message) {
        super(message);
    }
}