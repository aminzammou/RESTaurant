package com.restaurant.authentication.core.domain.entity;

import com.restaurant.authentication.core.domain.event.Event;
import com.restaurant.authentication.core.domain.event.UserRegistered;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class User {
    @Id
    private final String username;
    private String password;
    private Role role;
    @Transient
    private final List<Event> events = new ArrayList<>();

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.events.add(new UserRegistered(username));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void clearEvents() {
        events.clear();
    }
}