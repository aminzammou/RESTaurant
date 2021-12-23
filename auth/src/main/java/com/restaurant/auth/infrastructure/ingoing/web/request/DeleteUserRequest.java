package com.restaurant.auth.infrastructure.ingoing.web.request;

public record DeleteUserRequest(String token, String password) { }