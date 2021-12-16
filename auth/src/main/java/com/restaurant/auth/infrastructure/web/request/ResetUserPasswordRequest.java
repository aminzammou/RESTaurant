package com.restaurant.auth.infrastructure.web.request;

public record ResetUserPasswordRequest(String oldPassword, String newPassword) {
    public String getOldPassword() {
        return oldPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
}