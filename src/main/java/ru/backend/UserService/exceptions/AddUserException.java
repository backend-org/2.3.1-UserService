package ru.backend.UserService.exceptions;

public class AddUserException extends RuntimeException {
    public AddUserException(String message) {
        super(message);
    }
}
