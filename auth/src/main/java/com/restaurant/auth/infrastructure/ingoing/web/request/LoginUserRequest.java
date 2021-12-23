package com.restaurant.auth.infrastructure.ingoing.web.request;

public record LoginUserRequest(String username, String password) {}