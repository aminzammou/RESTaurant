package com.restaurant.auth.infrastructure.ingoing.web.response;

public record GetUserResponse(String username, String role, String firstName, String lastName, String gender) { }