package com.restaurant.authentication.core.domain.event;

public class UserRegistered extends Event {
    private final String username;

    public UserRegistered(String username) {
        this.username = username;
    }

    @Override
    public String getEventKey() {
        return "authentication.registered";
    }

    public String getUsername() {
        return username;
    }
}