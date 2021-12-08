package com.restaurant.authentication.core.application.handler;

import com.restaurant.authentication.core.application.command.ChangeUserRoleCommand;
import com.restaurant.authentication.core.application.command.RegisterUserCommand;
import com.restaurant.authentication.core.application.command.ResetUserPasswordCommand;
import com.restaurant.authentication.core.domain.entity.Role;
import com.restaurant.authentication.core.domain.entity.User;
import com.restaurant.authentication.core.domain.event.Event;
import com.restaurant.authentication.core.domain.exception.InvalidUserRole;
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
        User user = new User(command.getUsername(), command.getPassword(), Role.CUSTOMER);

        this.publishEvents(user);
        this.userRepository.save(user);

        return user;
    }

    public User handle(ResetUserPasswordCommand command) {
        User user = this.getUser(command.getUsername());
        user.setPassword(command.getPassword());

        this.publishEvents(user);
        this.userRepository.save(user);

        return user;
    }

    public User handle(ChangeUserRoleCommand command) {
        User user = this.getUser(command.getUsername());

        if(command.getRole() == "CUSTOMER") {
            user.setRole(Role.CUSTOMER);
        }
        else if(command.getRole() == "EMPLOYEE") {
            user.setRole(Role.EMPLOYEE);
        }
        else if(command.getRole() == "ADMIN") {
            user.setRole(Role.ADMIN);
        }
        else {
            throw new InvalidUserRole(command.getRole());
        }

        this.publishEvents(user);
        this.userRepository.save(user);

        return user;
    }

    private User getUser(String username) {
        return this.userRepository.findById(username)
                .orElseThrow(() -> new UserNotFound(username));
    }

    private void publishEvents(User user) {
        List<Event> events = user.getEvents();
        //events.forEach(eventPublisher::publish);
        user.clearEvents();
    }
}
