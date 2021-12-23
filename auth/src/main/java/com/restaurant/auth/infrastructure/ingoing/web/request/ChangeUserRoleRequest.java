package com.restaurant.auth.infrastructure.ingoing.web.request;

public record ChangeUserRoleRequest(String token, String username, String role) { }