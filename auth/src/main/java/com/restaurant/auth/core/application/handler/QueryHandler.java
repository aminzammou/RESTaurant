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
    private static final long MAX_TOKEN_AGE = 1800;

    public QueryHandler(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public FindUserDocument handle(FindUserQuery query) {
        // Validate token
        String username = validateToken(query.token());

        // Retrieve user
        if(this.userRepository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }
        User user = this.userRepository.findById(username).get();

        // Return user
        return new FindUserDocument(user.getUsername(), user.getRole().name(), user.getFirstName(), user.getLastName(), user.getGender().name());
    }

    private String validateToken(String tokenValue) {
        // Validate token existence
        if(this.tokenRepository.findById(tokenValue).isEmpty()) {
            throw new Unauthorized();
        }

        // Calculate token age
        Token token = this.tokenRepository.findById(tokenValue).get();
        long epoch = (System.currentTimeMillis() / 1000L);
        long tokenAge = (epoch - token.getEpoch());

        // Validate token age
        if(tokenAge > MAX_TOKEN_AGE) {
            throw new Unauthorized();
        }

        // Return token username
        return token.getUsername();
    }
}