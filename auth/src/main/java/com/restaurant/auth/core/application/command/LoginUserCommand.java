package com.restaurant.auth.core.application.command;

public record LoginUserCommand(String username, String password) { }