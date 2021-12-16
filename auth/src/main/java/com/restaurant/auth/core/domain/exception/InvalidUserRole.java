package com.restaurant.auth.core.domain.exception;

public class InvalidUserRole extends RuntimeException {
    public InvalidUserRole(String message) {
        super(message);
    }
}