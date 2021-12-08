package com.restaurant.authentication.core.application.command;

public record ChangeUserRoleCommand(String username, String role) {

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}