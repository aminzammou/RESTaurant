package com.restaurant.auth.infrastructure.web.request;

public record ChangeUserRoleRequest(String role) {
    public String getRole() {
        return role;
    }
}