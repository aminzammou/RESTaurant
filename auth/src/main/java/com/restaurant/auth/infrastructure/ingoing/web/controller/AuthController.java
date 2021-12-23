package com.restaurant.auth.infrastructure.ingoing.web.controller;

import com.restaurant.auth.core.application.command.*;
import com.restaurant.auth.core.application.document.FindUserDocument;
import com.restaurant.auth.core.application.document.LoginUserDocument;
import com.restaurant.auth.core.application.handler.CommandHandler;
import com.restaurant.auth.core.application.handler.QueryHandler;
import com.restaurant.auth.core.application.query.FindUserQuery;
import com.restaurant.auth.core.domain.exception.*;
import com.restaurant.auth.infrastructure.ingoing.web.request.*;
import com.restaurant.auth.infrastructure.ingoing.web.response.FindUserResponse;
import com.restaurant.auth.infrastructure.ingoing.web.response.LoginUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final CommandHandler commandHandler;
    private final QueryHandler queryHandler;

    public AuthController(CommandHandler commandHandler, QueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping("/command/register-user")
    public void registerUser(@RequestBody RegisterUserRequest request) {
        this.commandHandler.handle(new RegisterUserCommand(request.username(), request.password(), request.firstName(), request.lastName(), request.gender()));
    }

    @PostMapping("/command/login-user")
    public LoginUserResponse loginUser(@RequestBody LoginUserRequest request) {
        LoginUserDocument document = this.commandHandler.handle(new LoginUserCommand(request.username(), request.password()));
        return new LoginUserResponse(document.token());
    }

    @PostMapping("/command/logout-user")
    public void logoutUser(@RequestBody LogoutUserRequest request) {
        this.commandHandler.handle(new LogoutUserCommand(request.token()));
    }

    @PatchMapping("/command/change-user-password")
    public void changeUserPassword(@RequestBody ChangeUserPasswordRequest request) {
        this.commandHandler.handle(new ChangeUserPasswordCommand(request.token(), request.oldPassword(), request.newPassword()));
    }

    @PatchMapping("/command/change-user-role")
    public void changeUserRole(@RequestBody ChangeUserRoleRequest request) {
        this.commandHandler.handle(new ChangeUserRoleCommand(request.token(), request.username(), request.role()));
    }

    @PatchMapping("/command/change-user-info")
    public void changeUserInfo(@RequestBody ChangeUserInfoRequest request) {
        this.commandHandler.handle(new ChangeUserInfoCommand(request.token(), request.firstName(), request.lastName(), request.gender()));
    }

    @DeleteMapping("/command/delete-user")
    public void deleteUser(@RequestBody DeleteUserRequest request) {
        this.commandHandler.handle(new DeleteUserCommand(request.token(), request.password()));
    }

    @GetMapping("/query/find-user")
    public FindUserResponse findUser(@RequestBody FindUserRequest request) {
        FindUserDocument document = this.queryHandler.handle(new FindUserQuery(request.token()));
        return new FindUserResponse(document.username(), document.role(), document.firstName(), document.lastName(), document.gender());
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