package com.restaurant.authentication.core.application.handler;

import com.restaurant.authentication.core.application.command.ChangeUserRoleCommand;
import com.restaurant.authentication.core.application.command.RegisterUserCommand;
import com.restaurant.authentication.core.application.command.ResetUserPasswordCommand;
import com.restaurant.authentication.core.domain.entity.Role;
import com.restaurant.authentication.core.domain.entity.User;
import com.restaurant.authentication.core.domain.event.Event;
import com.restaurant.authentication.core.domain.exception.InvalidUserRole;
import com.restaurant.authentication.core.domain.exception.UserAlreadyExisting;
import com.restaurant.authentication.core.domain.exception.UserNotFound;
import com.restaurant.authentication.core.port.messaging.EventPublisher;
import com.restaurant.authentication.core.port.storage.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationCommandHandler {
    //private final EventPublisher eventPublisher;
    private final UserRepository userRepository;

    public AuthenticationCommandHandler(UserRepository repository) {
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
        user.setPassword(command.getPassword());

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

    private void publishEvents(User user) {
        List<Event> events = user.getEvents();
        //events.forEach(eventPublisher::publish);
        user.clearEvents();
    }
}
