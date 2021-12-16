package com.restaurant.auth.core.application.command;

public record DeleteUserCommand(String token, String password) { }