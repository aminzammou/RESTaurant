package com.restaurant.auth.core.application.handler;

import com.restaurant.auth.core.application.command.ChangeUserRoleCommand;
import com.restaurant.auth.core.application.command.DeleteUserCommand;
import com.restaurant.auth.core.application.command.RegisterUserCommand;
import com.restaurant.auth.core.application.command.ResetUserPasswordCommand;
import com.restaurant.auth.core.domain.entity.Role;
import com.restaurant.auth.core.domain.entity.User;
import com.restaurant.auth.core.domain.event.Event;
import com.restaurant.auth.core.domain.exception.IncorrectPassword;
import com.restaurant.auth.core.domain.exception.InvalidUserRole;
import com.restaurant.auth.core.domain.exception.UserAlreadyExisting;
import com.restaurant.auth.core.domain.exception.UserNotFound;
import com.restaurant.auth.core.port.storage.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthCommandHandler {
    //private final EventPublisher eventPublisher;
    private final UserRepository userRepository;

    public AuthCommandHandler(UserRepository repository) {
        //this.eventPublisher = publisher;
        this.userRepository = repository;
    }

    public User handle(RegisterUserCommand command) {
        if(this.userRepository.findById(command.getUsername()).isPresent()) {
            throw new UserAlreadyExisting(command.getUsername());
        }

        User user = new User(command.getUsername(), command.getPassword(), Role.CUSTOMER);

        this.publishEvents(user);
        this.userRepository.save(user);

        return user;
    }

    public User handle(ResetUserPasswordCommand command) {
        if(this.userRepository.findById(command.getUsername()).isEmpty()) {
            throw new UserNotFound(command.getUsername());
        }

        User user = this.userRepository.findById(command.getUsername()).get();
        if (!command.getOldPassword().equals(user.getPassword())) {
            throw new IncorrectPassword(command.getUsername());
        }
        user.setPassword(command.getNewPassword());

        this.publishEvents(user);
        this.userRepository.save(user);

        return user;
    }

    public User handle(ChangeUserRoleCommand command) {
        if(this.userRepository.findById(command.getUsername()).isEmpty()) {
            throw new UserNotFound(command.getUsername());
        }

        User user = this.userRepository.findById(command.getUsername()).get();

        switch (command.getRole()) {
            case "CUSTOMER" -> user.setRole(Role.CUSTOMER);
            case "EMPLOYEE" -> user.setRole(Role.EMPLOYEE);
            case "ADMIN" -> user.setRole(Role.ADMIN);
            default -> throw new InvalidUserRole(command.getRole());
        }

        this.publishEvents(user);
        this.userRepository.save(user);

        return user;
    }

    public User handle(DeleteUserCommand command) {
        if(this.userRepository.findById(command.getUsername()).isEmpty()) {
            throw new UserNotFound(command.getUsername());
        }

        User user = this.userRepository.findById(command.getUsername()).get();

        this.publishEvents(user);
        this.userRepository.delete(user);

        return user;
    }

    private void publishEvents(User user) {
        List<Event> events = user.getEvents();
        //events.forEach(eventPublisher::publish);
        user.clearEvents();
    }
}
