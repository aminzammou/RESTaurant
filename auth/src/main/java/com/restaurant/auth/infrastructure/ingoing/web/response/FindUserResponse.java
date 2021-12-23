package com.restaurant.auth.infrastructure.ingoing.web.response;

public record FindUserResponse(String username, String role, String firstName, String lastName, String gender) { }