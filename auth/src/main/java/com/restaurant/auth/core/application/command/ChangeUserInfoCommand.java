package com.restaurant.auth.core.application.command;

public record ChangeUserInfoCommand(String token, String firstName, String lastName, String gender) { }