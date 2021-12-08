package com.restaurant.authentication.infrastructure.web.request;

public record ChangeUserRoleRequest(String role) {
    public String getRole() {
        return role;
    }
}