package com.restaurant.authentication.core.application.command;

public record RegisterUserCommand(String username, String password) {
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}