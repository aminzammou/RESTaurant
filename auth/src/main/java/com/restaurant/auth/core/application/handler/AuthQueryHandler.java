package com.restaurant.auth.core.application.handler;

import com.restaurant.auth.core.application.document.GetUserDocument;
import com.restaurant.auth.core.application.query.GetUserQuery;
import com.restaurant.auth.core.domain.element.Token;
import com.restaurant.auth.core.domain.element.User;
import com.restaurant.auth.core.domain.exception.Unauthorized;
import com.restaurant.auth.core.port.storage.TokenRepository;
import com.restaurant.auth.core.port.storage.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthQueryHandler {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public AuthQueryHandler(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public GetUserDocument handle(GetUserQuery query) {
        if(this.tokenRepository.findById(query.token()).isEmpty()) {
            throw new Unauthorized();
        }

        Token token = this.tokenRepository.findById(query.token()).get();

        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.userRepository.findById(token.getUsername()).get();

        return new GetUserDocument(user.getUsername(), user.getRole().name(), user.getFirstName(), user.getLastName(), user.getGender().name());
    }
}