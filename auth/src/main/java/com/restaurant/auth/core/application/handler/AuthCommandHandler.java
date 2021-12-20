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
    private final UserRepository repository;

    public AuthCommandHandler(UserRepository repository) {
        this.repository = repository;

        // Create admin account
        if(this.repository.findById("admin").isEmpty()) {
            User admin = new User("admin", "admin", Role.ADMIN, "Ad", "Min", Gender.OTHER);
            this.repository.save(admin);
        }
    }

    public void handle(RegisterUserCommand command) {
        if(this.repository.findById(command.username()).isPresent()) {
            throw new UserAlreadyExists();
        }

        User user = new User(command.username(), command.password(), Role.CUSTOMER, command.firstName(), command.lastName(), Gender.valueOf(command.gender()));

        this.repository.save(user);
    }

    public String handle(LoginUserCommand command) {
        if (this.repository.findById(command.username()).isEmpty()) {
            throw new UserNotFound();
        }

        User user = this.repository.findById(command.username()).get();

        if (!command.password().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        return new StringBuilder(command.username()).reverse().toString();
    }

    public void handle(ChangeUserPasswordCommand command) {
        String username = new StringBuilder(command.token()).reverse().toString();

        if(this.repository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.repository.findById(username).get();

        if (!command.oldPassword().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        user.setPassword(command.newPassword());

        this.repository.save(user);
    }

    public void handle(ChangeUserRoleCommand command) {
        String adminUsername = new StringBuilder(command.token()).reverse().toString();

        if(this.repository.findById(adminUsername).isEmpty()) {
            throw new Unauthorized();
        }

        User adminUser = this.repository.findById(adminUsername).get();

        if(!adminUser.getRole().equals(Role.ADMIN)){
            throw new Unauthorized();
        }

        if(this.repository.findById(command.username()).isEmpty()) {
            throw new UserNotFound();
        }

        User user = this.repository.findById(command.username()).get();

        try {
            user.setRole(Role.valueOf(command.role()));
        }catch (Exception e){
            throw new InvalidRole();
        }

        this.repository.save(user);
    }

    public void handle(ChangeUserInfoCommand command) {
        String username = new StringBuilder(command.token()).reverse().toString();

        if(this.repository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.repository.findById(username).get();

        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());

        try {
            user.setGender(Gender.valueOf(command.gender()));
        }catch (Exception e){
            throw new InvalidRole();
        }

        this.repository.save(user);
    }

    public void handle(DeleteUserCommand command) {
        String username = new StringBuilder(command.token()).reverse().toString();

        if (this.repository.findById(username).isEmpty()) {
            throw new Unauthorized();
        }

        User user = this.repository.findById(username).get();

        if (!command.password().equals(user.getPassword())) {
            throw new IncorrectPassword();
        }

        this.repository.delete(user);
    }
}