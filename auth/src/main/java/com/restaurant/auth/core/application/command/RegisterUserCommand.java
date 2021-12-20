package com.restaurant.auth.core.application.command;

public record RegisterUserCommand(String username, String password, String firstName, String lastName, String gender) { }