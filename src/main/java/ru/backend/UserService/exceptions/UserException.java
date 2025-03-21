package ru.backend.UserService.exceptions;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
