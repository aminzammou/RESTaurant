package com.restaurant.auth.core.domain.exception;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("");
    }
}