package com.restaurant.auth.core.application.handler;

import com.restaurant.auth.core.application.document.FindUserDocument;
import com.restaurant.auth.core.application.query.FindUserQuery;
import com.restaurant.auth.core.domain.element.Token;
import com.restaurant.auth.core.domain.element.User;
import com.restaurant.auth.core.domain.exception.Unauthorized;
import com.restaurant.auth.core.port.storage.TokenRepository;
import com.restaurant.auth.core.port.storage.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class QueryHandler {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public QueryHandler(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public FindUserDocument handle(FindUserQuery query) {
        // Retrieve token
        if(this.tokenRepository.findById(query.token()).isEmpty()) {
            throw new Unauthorized();
        }
        Token token = this.tokenRepository.findById(query.token()).get();

        // Retrieve user
        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }
        User user = this.userRepository.findById(token.getUsername()).get();

        // Return user
        return new FindUserDocument(user.getUsername(), user.getRole().name(), user.getFirstName(), user.getLastName(), user.getGender().name());
    }
}