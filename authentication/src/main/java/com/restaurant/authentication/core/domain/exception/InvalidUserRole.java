package com.restaurant.authentication.core.domain.exception;

public class InvalidUserRole extends RuntimeException {
    public InvalidUserRole(String message) {
        super(message);
    }
}