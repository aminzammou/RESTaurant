package com.restaurant.auth.core.application.command;

public record ResetUserPasswordCommand(String username, String oldPassword, String newPassword) {
    public String getUsername() {
        return username;
    }
    public String getOldPassword() {
        return oldPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
}