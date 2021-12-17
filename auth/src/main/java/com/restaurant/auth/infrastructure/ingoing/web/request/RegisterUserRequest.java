package com.restaurant.auth.infrastructure.ingoing.web.request;

public record RegisterUserRequest(String username, String password, String firstName, String lastName, String gender) {}