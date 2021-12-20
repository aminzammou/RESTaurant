package com.restaurant.auth.core.application.document;

public record GetUserDocument(String username, String role, String firstName, String lastName, String gender) { }