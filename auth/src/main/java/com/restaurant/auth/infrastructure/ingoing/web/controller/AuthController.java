package com.restaurant.auth.infrastructure.ingoing.web.controller;

import com.restaurant.auth.core.application.command.*;
import com.restaurant.auth.core.application.document.GetUserDocument;
import com.restaurant.auth.core.application.handler.AuthCommandHandler;
import com.restaurant.auth.core.application.handler.AuthQueryHandler;
import com.restaurant.auth.core.application.query.GetUserQuery;
import com.restaurant.auth.core.domain.exception.IncorrectPassword;
import com.restaurant.auth.core.domain.exception.InvalidRole;
import com.restaurant.auth.core.domain.exception.UserAlreadyExists;
import com.restaurant.auth.core.domain.exception.UserNotFound;
import com.restaurant.auth.infrastructure.ingoing.web.request.*;
import com.restaurant.auth.infrastructure.ingoing.web.response.GetUserResponse;
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
    public void registerUser(@RequestBody RegisterUserRequest request) {
        this.commandHandler.handle(new RegisterUserCommand(request.username(), request.password(), request.firstName(), request.lastName(), request.gender()));
    }

    @PatchMapping("/user/password/{username}")
    public void changeUserPassword(@PathVariable String token, @RequestBody ChangeUserPasswordRequest request) {
        this.commandHandler.handle(new ChangeUserPasswordCommand(token, request.oldPassword(), request.newPassword()));
    }

    @PatchMapping("/user/role/{username}")
    public void changeUserRole(@PathVariable String token, @RequestBody ChangeUserRoleRequest request) {
        this.commandHandler.handle(new ChangeUserRoleCommand(token, request.username(), request.role()));
    }

    @PatchMapping("/user/info/{username}")
    public void changeUserInfo(@PathVariable String token, @RequestBody ChangeUserInfoRequest request) {
        this.commandHandler.handle(new ChangeUserInfoCommand(token, request.firstName(), request.lastName(), request.gender()));
    }

    @DeleteMapping("/user/{username}")
    public void deleteUser(@PathVariable String token, @RequestBody DeleteUserRequest request) {
        this.commandHandler.handle(new DeleteUserCommand(token, request.password()));
    }

    @GetMapping("/user/{username}")
    public GetUserResponse getUser(@PathVariable String token) {
        GetUserDocument document = this.queryHandler.handle(new GetUserQuery(token));
        return new GetUserResponse(document.username(), document.role(), document.firstName(), document.lastName(), document.gender());
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleUserAlreadyExisting(UserAlreadyExists exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleUserNotFound(UserNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleIncorrectUserPassword(IncorrectPassword exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleInvalidUserRole(InvalidRole exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}