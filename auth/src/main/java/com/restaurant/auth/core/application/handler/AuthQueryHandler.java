package com.restaurant.auth.core.application.handler;

import com.restaurant.auth.core.application.query.GetUserQuery;
import com.restaurant.auth.core.domain.entity.User;
import com.restaurant.auth.core.domain.exception.UserNotFound;
import com.restaurant.auth.core.port.storage.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthQueryHandler {
    private final UserRepository repository;

    public AuthQueryHandler(UserRepository repository) {
        this.repository = repository;
    }

    public User handle(GetUserQuery query) {
        return this.repository.findById(query.getUsername())
                .orElseThrow(() -> new UserNotFound(query.getUsername()));
    }
}
