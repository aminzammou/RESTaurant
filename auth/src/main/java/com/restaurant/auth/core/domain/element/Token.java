package com.restaurant.auth.core.domain.element;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Token {
    @Id
    @Field
    private final String value;

    @Field
    @Indexed
    private final String username;

    public Token(String value, String username) {
        this.value = value;
        this.username = username;
    }

    public String getValue() {
        return value;
    }

    public String getUsername() {
        return username;
    }
}