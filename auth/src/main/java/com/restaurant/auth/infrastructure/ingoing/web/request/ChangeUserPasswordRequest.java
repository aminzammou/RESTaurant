package com.restaurant.auth.infrastructure.ingoing.web.request;

public record ChangeUserPasswordRequest(String oldPassword, String newPassword) { }