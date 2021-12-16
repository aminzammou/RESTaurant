package com.restaurant.auth.core.application.command;

public record DeleteUserCommand(String username) {
    public String getUsername() {
        return username;
    }
}