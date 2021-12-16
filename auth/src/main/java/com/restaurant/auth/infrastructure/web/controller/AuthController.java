package com.restaurant.auth.infrastructure.web.controller;

import com.restaurant.auth.core.application.command.ChangeUserRoleCommand;
import com.restaurant.auth.core.application.command.DeleteUserCommand;
import com.restaurant.auth.core.application.command.RegisterUserCommand;
import com.restaurant.auth.core.application.command.ResetUserPasswordCommand;
import com.restaurant.auth.core.application.handler.AuthCommandHandler;
import com.restaurant.auth.core.application.handler.AuthQueryHandler;
import com.restaurant.auth.core.application.query.GetUserQuery;
import com.restaurant.auth.core.domain.entity.User;
import com.restaurant.auth.core.domain.exception.InvalidUserRole;
import com.restaurant.auth.core.domain.exception.UserAlreadyExisting;
import com.restaurant.auth.core.domain.exception.UserNotFound;
import com.restaurant.auth.infrastructure.web.request.ChangeUserRoleRequest;
import com.restaurant.auth.infrastructure.web.request.RegisterUserRequest;
import com.restaurant.auth.infrastructure.web.request.ResetUserPasswordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthCommandHandler commandHandler;
    private final AuthQueryHandler queryHandler;

    public AuthController(AuthCommandHandler commandHandler, AuthQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping("/user")
    public User registerUser(@RequestBody RegisterUserRequest request) {
        return this.commandHandler.handle(
                new RegisterUserCommand(request.getUsername(), request.getPassword())
        );
    }

    @PatchMapping("/user/password/{username}")
    public User resetUserPassword(@PathVariable String username, @RequestBody ResetUserPasswordRequest request) {
        return this.commandHandler.handle(
                new ResetUserPasswordCommand(username, request.getOldPassword(), request.getNewPassword())
        );
    }

    @PatchMapping("/user/role/{username}")
    public User changeUserRole(@PathVariable String username, @RequestBody ChangeUserRoleRequest request) {
        return this.commandHandler.handle(
                new ChangeUserRoleCommand(username, request.getRole())
        );
    }

    @DeleteMapping("/user/{username}")
    public User deleteUser(@PathVariable String username) {
        return this.commandHandler.handle(
                new DeleteUserCommand(username)
        );
    }

    @GetMapping("/user/{username}")
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
