package com.restaurant.auth.core.application.document;

public record FindUserDocument(String username, String role, String firstName, String lastName, String gender) { }