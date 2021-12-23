package com.restaurant.auth.infrastructure.ingoing.web.request;

public record ChangeUserPasswordRequest(String token, String oldPassword, String newPassword) { }