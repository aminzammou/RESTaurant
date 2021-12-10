package com.restaurant.authentication.core.application.command;

public record ResetUserPasswordCommand(String username, String password) {
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}