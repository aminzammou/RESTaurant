package com.restaurant.auth.infrastructure.ingoing.web.controller;

import com.restaurant.auth.core.application.command.*;
import com.restaurant.auth.core.application.document.GetUserDocument;
import com.restaurant.auth.core.application.document.LoginUserDocument;
import com.restaurant.auth.core.application.handler.AuthCommandHandler;
import com.restaurant.auth.core.application.handler.AuthQueryHandler;
import com.restaurant.auth.core.application.query.GetUserQuery;
import com.restaurant.auth.core.domain.exception.*;
import com.restaurant.auth.infrastructure.ingoing.web.request.*;
import com.restaurant.auth.infrastructure.ingoing.web.response.GetUserResponse;
import com.restaurant.auth.infrastructure.ingoing.web.response.LoginUserResponse;
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

    @PostMapping("/user/{username}")
    public LoginUserResponse loginUser(@PathVariable String username, @RequestBody LoginUserRequest request) {
        LoginUserDocument document = this.commandHandler.handle(new LoginUserCommand(username, request.password()));
        return new LoginUserResponse(document.token());
    }

    @PatchMapping("/user/password/{token}")
    public void changeUserPassword(@PathVariable String token, @RequestBody ChangeUserPasswordRequest request) {
        this.commandHandler.handle(new ChangeUserPasswordCommand(token, request.oldPassword(), request.newPassword()));
    }

    @PatchMapping("/user/role/{token}")
    public void changeUserRole(@PathVariable String token, @RequestBody ChangeUserRoleRequest request) {
        this.commandHandler.handle(new ChangeUserRoleCommand(token, request.username(), request.role()));
    }

    @PatchMapping("/user/info/{token}")
    public void changeUserInfo(@PathVariable String token, @RequestBody ChangeUserInfoRequest request) {
        this.commandHandler.handle(new ChangeUserInfoCommand(token, request.firstName(), request.lastName(), request.gender()));
    }

    @DeleteMapping("/user/{token}")
    public void deleteUser(@PathVariable String token, @RequestBody DeleteUserRequest request) {
        this.commandHandler.handle(new DeleteUserCommand(token, request.password()));
    }

    @GetMapping("/user/{token}")
    public GetUserResponse getUser(@PathVariable String token) {
        GetUserDocument document = this.queryHandler.handle(new GetUserQuery(token));
        return new GetUserResponse(document.username(), document.role(), document.firstName(), document.lastName(), document.gender());
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleUserAlreadyExistsException(UserAlreadyExists exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleUserNotFoundException(UserNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleIncorrectUserPasswordException(IncorrectPassword exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleInvalidUserRoleException(InvalidRole exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleUnauthorizedException(Unauthorized exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}