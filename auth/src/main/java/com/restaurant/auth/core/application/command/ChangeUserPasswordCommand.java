package com.restaurant.auth.core.application.command;

public record ChangeUserPasswordCommand(String token, String oldPassword, String newPassword) { }