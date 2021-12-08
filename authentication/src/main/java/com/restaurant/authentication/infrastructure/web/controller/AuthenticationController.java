package com.restaurant.authentication.infrastructure.web.controller;

import com.restaurant.authentication.core.application.command.ChangeUserRoleCommand;
import com.restaurant.authentication.core.application.command.RegisterUserCommand;
import com.restaurant.authentication.core.application.command.ResetUserPasswordCommand;
import com.restaurant.authentication.core.application.handler.AuthenticationCommandHandler;
import com.restaurant.authentication.core.application.handler.AuthenticationQueryHandler;
import com.restaurant.authentication.core.application.query.GetUserQuery;
import com.restaurant.authentication.core.domain.entity.User;
import com.restaurant.authentication.core.domain.exception.InvalidUserRole;
import com.restaurant.authentication.core.domain.exception.UserAlreadyExisting;
import com.restaurant.authentication.core.domain.exception.UserNotFound;
import com.restaurant.authentication.infrastructure.web.request.ChangeUserRoleRequest;
import com.restaurant.authentication.infrastructure.web.request.RegisterUserRequest;
import com.restaurant.authentication.infrastructure.web.request.ResetUserPasswordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationCommandHandler commandHandler;
    private final AuthenticationQueryHandler queryHandler;

    public AuthenticationController(AuthenticationCommandHandler commandHandler, AuthenticationQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping("/user")
    public User registerUser(@RequestBody RegisterUserRequest request) {
        return this.commandHandler.handle(
                new RegisterUserCommand(request.getUsername(), request.getPassword())
        );
    }

    @PatchMapping("/user/password/{id}")
    public User resetUserPassword(@PathVariable String username, @RequestBody ResetUserPasswordRequest request) {
        return this.commandHandler.handle(
                new ResetUserPasswordCommand(username, request.getPassword())
        );
    }

    @PatchMapping("/user/role/{id}")
    public User changeUserRole(@PathVariable String username, @RequestBody ChangeUserRoleRequest request) {
        return this.commandHandler.handle(
                new ChangeUserRoleCommand(username, request.getRole())
        );
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String username) {
        return this.queryHandler.handle(new GetUserQuery(username));
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleUserAlreadyExisting(UserAlreadyExisting exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleUserNotFound(UserNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleInvalidUserRole(InvalidUserRole exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
