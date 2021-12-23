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
import java.util.Base64;

@Service
public class CommandHandler {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private static final long MAX_TOKEN_AGE = 1800;

    public CommandHandler(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;

        // Create admin account
        if(this.userRepository.findById("admin").isEmpty()) {
            User admin = new User("admin", "admin", Role.ADMIN, "Ad", "Min", Gender.OTHER);
            this.userRepository.save(admin);
        }
    }

    public void handle(RegisterUserCommand command) {
        // Validate username
        if(this.userRepository.findById(command.username()).isPresent()) {
            throw new UserAlreadyExists();
        }

        // Create new user
        User user = new User(command.username(), command.password(), Role.CUSTOMER, command.firstName(), command.lastName(), Gender.valueOf(command.gender()));
        this.userRepository.save(user);
    }

    public LoginUserDocument handle(LoginUserCommand command) {
        // Retrieve user
        if (this.userRepository.findById(command.username()).isEmpty()) {
            throw new UserNotFound();
        }
        User user = this.userRepository.findById(command.username()).get();

        // Validate password
        if (!command.password().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        // Generate random 32 character string with SHA1PRNG
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String tokenValue = base64Encoder.encodeToString(randomBytes);
        long epoch = (System.currentTimeMillis() / 1000L);
        Token token = new Token(tokenValue, command.username(), epoch);
        this.tokenRepository.save(token);

        // Return token value
        return new LoginUserDocument(token.getValue());
    }

    public void handle(LogoutUserCommand command) {
        // Validate token
        String username = validateToken(command.token());

        // Validate username
        if(this.userRepository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }

        // Delete token
        this.tokenRepository.deleteById(command.token());
    }

    public void handle(ChangeUserPasswordCommand command) {
        // Validate token
        String username = validateToken(command.token());

        // Retrieve user
        if(this.userRepository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }
        User user = this.userRepository.findById(username).get();

        // Set password
        if (!command.oldPassword().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }
        user.setPassword(command.newPassword());

        // Save changes
        this.userRepository.save(user);
    }

    public void handle(ChangeUserRoleCommand command) {
        // Validate token
        String username = validateToken(command.token());

        // Retrieve admin
        if(this.userRepository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }
        User admin = this.userRepository.findById(username).get();

        // Validate role
        if(!admin.getRole().equals(Role.ADMIN)){
            throw new Unauthorized();
        }

        // Retrieve user
        if(this.userRepository.findById(command.username()).isEmpty()) {
            throw new UserNotFound();
        }
        User user = this.userRepository.findById(command.username()).get();

        // Set role
        try {
            user.setRole(Role.valueOf(command.role()));
        }catch (Exception e){
            throw new InvalidRole();
        }

        // Save changes
        this.userRepository.save(user);
    }

    public void handle(ChangeUserInfoCommand command) {
        // Validate token
        String username = validateToken(command.token());

        // Retrieve user
        if(this.userRepository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }
        User user = this.userRepository.findById(username).get();

        // Set names
        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());

        // Set gender
        try {
            user.setGender(Gender.valueOf(command.gender()));
        }catch (Exception e){
            throw new InvalidRole();
        }

        // Save changes
        this.userRepository.save(user);
    }

    public void handle(DeleteUserCommand command) {
        // Validate token
        String username = validateToken(command.token());

        // Retrieve user
        if(this.userRepository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }
        User user = this.userRepository.findById(username).get();

        // Validate password
        if (!command.password().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        // Delete user
        this.userRepository.delete(user);
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