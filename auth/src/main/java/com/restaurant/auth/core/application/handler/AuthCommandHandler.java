package com.restaurant.auth.core.application.handler;

import com.restaurant.auth.core.application.command.*;
import com.restaurant.auth.core.domain.element.Gender;
import com.restaurant.auth.core.domain.element.Role;
import com.restaurant.auth.core.domain.element.User;
import com.restaurant.auth.core.domain.exception.*;
import com.restaurant.auth.core.port.storage.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthCommandHandler {
    private final UserRepository userRepository;

    public AuthCommandHandler(UserRepository repository) {
        this.userRepository = repository;
    }

    public void handle(RegisterUserCommand command) {
        if(this.userRepository.findById(command.username()).isPresent()) {
            throw new UserAlreadyExists();
        }

        User user = new User(command.username(), command.password(), Role.CUSTOMER, command.firstName(), command.lastName(), Gender.valueOf(command.gender()));

        this.userRepository.save(user);
    }

    public void handle(ChangeUserPasswordCommand command) {
        String username = new StringBuilder(command.token()).reverse().toString();

        if(this.userRepository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.userRepository.findById(username).get();

        if (!command.oldPassword().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        user.setPassword(command.newPassword());
    }

    public void handle(ChangeUserRoleCommand command) {
        String adminUsername = new StringBuilder(command.token()).reverse().toString();

        if(this.userRepository.findById(adminUsername).isEmpty()) {
            throw new Unauthorized();
        }

        User adminUser = this.userRepository.findById(adminUsername).get();

        if(!adminUser.getRole().equals(Role.ADMIN)){
            throw new Unauthorized();
        }

        if(this.userRepository.findById(command.username()).isEmpty()) {
            throw new UserNotFound();
        }

        User user = this.userRepository.findById(command.username()).get();

        user.setRole(Role.valueOf(command.role()));
        // throw new InvalidRole();
    }

    public void handle(ChangeUserInfoCommand command) {
        String username = new StringBuilder(command.token()).reverse().toString();

        if(this.userRepository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.userRepository.findById(username).get();

        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setGender(Gender.valueOf(command.gender()));
    }

    public void handle(DeleteUserCommand command) {
        String username = new StringBuilder(command.token()).reverse().toString();

        if (this.userRepository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.userRepository.findById(username).get();

        if (!command.password().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        this.userRepository.delete(user);
    }
}