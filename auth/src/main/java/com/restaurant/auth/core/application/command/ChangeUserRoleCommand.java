package com.restaurant.auth.core.application.command;

public record ChangeUserRoleCommand(String token, String username, String role) { }