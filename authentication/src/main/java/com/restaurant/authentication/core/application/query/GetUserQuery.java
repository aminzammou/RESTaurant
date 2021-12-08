package com.restaurant.authentication.core.application.query;

public record GetUserQuery(String username) {

    public String getUsername() {
        return username;
    }
}