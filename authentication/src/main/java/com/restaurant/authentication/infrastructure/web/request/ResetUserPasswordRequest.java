package com.restaurant.authentication.infrastructure.web.request;

public record ResetUserPasswordRequest(String password) {
    public String getPassword() {
        return password;
    }
}