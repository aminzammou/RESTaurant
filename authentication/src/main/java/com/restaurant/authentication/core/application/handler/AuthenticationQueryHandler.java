package com.restaurant.authentication.core.application.handler;

import com.restaurant.authentication.core.application.query.GetUserQuery;
import com.restaurant.authentication.core.domain.entity.User;
import com.restaurant.authentication.core.domain.exception.UserNotFound;
import com.restaurant.authentication.core.port.storage.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationQueryHandler {
    private final UserRepository repository;

    public AuthenticationQueryHandler(UserRepository repository) {
        this.repository = repository;
    }

    public User handle(GetUserQuery query) {
        return this.repository.findById(query.getUsername())
                .orElseThrow(() -> new UserNotFound(query.getUsername()));
    }
}
