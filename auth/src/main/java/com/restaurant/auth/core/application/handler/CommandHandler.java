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
    private final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

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
        Token token = new Token(tokenValue, command.username());
        this.tokenRepository.save(token);

        // Return token value
        return new LoginUserDocument(token.getValue());
    }

    public void handle(LogoutUserCommand command) {
        // Retrieve token
        if(this.tokenRepository.findById(command.token()).isEmpty()) {
            throw new Unauthorized();
        }
        Token token = this.tokenRepository.findById(command.token()).get();

        // Validate username
        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }

        // Delete token
        this.tokenRepository.deleteById(command.token());
    }

    public void handle(ChangeUserPasswordCommand command) {
        // Retrieve token
        if(this.tokenRepository.findById(command.token()).isEmpty()) {
            throw new Unauthorized();
        }
        Token token = this.tokenRepository.findById(command.token()).get();

        // Retrieve user
        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }
        User user = this.userRepository.findById(token.getUsername()).get();

        // Set password
        if (!command.oldPassword().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }
        user.setPassword(command.newPassword());

        // Save changes
        this.userRepository.save(user);
    }

    public void handle(ChangeUserRoleCommand command) {
        // Retrieve token
        if(this.tokenRepository.findById(command.token()).isEmpty()) {
            throw new Unauthorized();
        }
        Token token = this.tokenRepository.findById(command.token()).get();

        // Retrieve admin
        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }
        User admin = this.userRepository.findById(token.getUsername()).get();

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
        // Retrieve token
        if(this.tokenRepository.findById(command.token()).isEmpty()) {
            throw new Unauthorized();
        }
        Token token = this.tokenRepository.findById(command.token()).get();

        // Retrieve user
        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }
        User user = this.userRepository.findById(token.getUsername()).get();

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
        // Retrieve token
        if(this.tokenRepository.findById(command.token()).isEmpty()) {
            throw new Unauthorized();
        }
        Token token = this.tokenRepository.findById(command.token()).get();

        // Retrieve user
        if(this.userRepository.findById(token.getUsername()).isEmpty()) {
            throw new Unauthorized();
        }
        User user = this.userRepository.findById(token.getUsername()).get();

        // Validate password
        if (!command.password().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        // Delete user
        this.userRepository.delete(user);
    }
}