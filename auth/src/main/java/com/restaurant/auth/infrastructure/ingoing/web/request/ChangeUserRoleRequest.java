package com.restaurant.auth.infrastructure.ingoing.web.request;

public record ChangeUserRoleRequest(String username, String role) { }