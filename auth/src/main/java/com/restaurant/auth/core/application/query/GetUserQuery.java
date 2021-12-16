package com.restaurant.auth.core.application.query;

public record GetUserQuery(String username) {

    public String getUsername() {
        return username;
    }
}