package com.restaurant.auth.core.application.handler;

import com.restaurant.auth.core.application.document.GetUserDocument;
import com.restaurant.auth.core.application.query.GetUserQuery;
import com.restaurant.auth.core.domain.element.User;
import com.restaurant.auth.core.domain.exception.Unauthorized;
import com.restaurant.auth.core.port.storage.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthQueryHandler {
    private final UserRepository repository;

    public AuthQueryHandler(UserRepository repository) {
        this.repository = repository;
    }

    public GetUserDocument handle(GetUserQuery query) {
        String username = new StringBuilder(query.token()).reverse().toString();

        if(this.repository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.repository.findById(username).get();

        return new GetUserDocument(user.getUsername(), user.getRole().name(), user.getFirstName(), user.getLastName(), user.getGender().name());
    }
}