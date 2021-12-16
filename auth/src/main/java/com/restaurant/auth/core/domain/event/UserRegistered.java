package com.restaurant.auth.core.domain.event;

public class UserRegistered extends Event {
    private final String username;

    public UserRegistered(String username) {
        this.username = username;
    }

    @Override
    public String getEventKey() {
        return "auth.registered";
    }

    public String getUsername() {
        return username;
    }
}