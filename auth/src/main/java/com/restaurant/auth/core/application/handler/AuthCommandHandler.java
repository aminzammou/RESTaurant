package com.restaurant.auth.core.application.handler;

import com.restaurant.auth.core.application.command.*;
import com.restaurant.auth.core.application.document.LoginUserDocument;
import com.restaurant.auth.core.domain.element.Gender;
import com.restaurant.auth.core.domain.element.Role;
import com.restaurant.auth.core.domain.element.Token;
import com.restaurant.auth.core.domain.element.User;
import com.restaurant.auth.core.domain.exception.*;
import com.restaurant.auth.core.port.storage.TokenRepository;
import com.restaurant.auth.core.port.storage.UserRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Service
public class AuthCommandHandler {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final SecureRandom secureRandom;
    private static Base64.Encoder base64Encoder;

    public AuthCommandHandler(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.secureRandom = new SecureRandom();
        this.base64Encoder = Base64.getUrlEncoder();

        // Create admin account
        if(this.userRepository.findById("admin").isEmpty()) {
            User admin = new User("admin", "admin", Role.ADMIN, "Ad", "Min", Gender.OTHER);
            this.userRepository.save(admin);
        }
    }

    public void handle(RegisterUserCommand command) {
        if(this.userRepository.findById(command.username()).isPresent()) {
            throw new UserAlreadyExists();
        }

        User user = new User(command.username(), command.password(), Role.CUSTOMER, command.firstName(), command.lastName(), Gender.valueOf(command.gender()));

        this.userRepository.save(user);
    }

    public LoginUserDocument handle(LoginUserCommand command) {
        if (this.userRepository.findById(command.username()).isEmpty()) {
            throw new UserNotFound();
        }

        User user = this.userRepository.findById(command.username()).get();

        if (!command.password().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String tokenValue = base64Encoder.encodeToString(randomBytes);
        Token token = new Token(tokenValue, command.username());
        this.tokenRepository.save(token);

        return new LoginUserDocument(token.getValue());
    }

    public void handle(ChangeUserPasswordCommand command) {
        if(this.tokenRepository.findById(command.token()).isEmpty()) {
            throw new Unauthorized();
        }

        Token token = this.tokenRepository.findById(command.token()).get();

        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.userRepository.findById(token.getUsername()).get();

        if (!command.oldPassword().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        user.setPassword(command.newPassword());

        this.userRepository.save(user);
    }

    public void handle(ChangeUserRoleCommand command) {
        if(this.tokenRepository.findById(command.token()).isEmpty()) {
            throw new Unauthorized();
        }

        Token token = this.tokenRepository.findById(command.token()).get();

        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }

        User adminUser = this.userRepository.findById(token.getUsername()).get();

        if(!adminUser.getRole().equals(Role.ADMIN)){
            throw new Unauthorized();
        }

        if(this.userRepository.findById(command.username()).isEmpty()) {
            throw new UserNotFound();
        }

        User user = this.userRepository.findById(command.username()).get();

        try {
            user.setRole(Role.valueOf(command.role()));
        }catch (Exception e){
            throw new InvalidRole();
        }

        this.userRepository.save(user);
    }

    public void handle(ChangeUserInfoCommand command) {
        if(this.tokenRepository.findById(command.token()).isEmpty()) {
            throw new Unauthorized();
        }

        Token token = this.tokenRepository.findById(command.token()).get();

        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.userRepository.findById(token.getUsername()).get();

        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());

        try {
            user.setGender(Gender.valueOf(command.gender()));
        }catch (Exception e){
            throw new InvalidRole();
        }

        this.userRepository.save(user);
    }

    public void handle(DeleteUserCommand command) {
        if(this.tokenRepository.findById(command.token()).isEmpty()) {
            throw new Unauthorized();
        }

        Token token = this.tokenRepository.findById(command.token()).get();

        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.userRepository.findById(token.getUsername()).get();

        if (!command.password().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        this.userRepository.delete(user);
    }
}