package com.restaurant.auth.infrastructure.web.request;

public record RegisterUserRequest(String username, String password) {
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}