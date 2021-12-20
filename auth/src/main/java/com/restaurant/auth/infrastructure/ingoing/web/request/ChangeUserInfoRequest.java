package com.restaurant.auth.infrastructure.ingoing.web.request;

public record ChangeUserInfoRequest(String token, String firstName, String lastName, String gender) {}