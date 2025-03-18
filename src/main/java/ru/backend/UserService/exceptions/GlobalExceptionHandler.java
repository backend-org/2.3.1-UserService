package ru.backend.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.backend.UserService.model.dto.AppUserErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AddUserException.class)
    public ResponseEntity<AppUserErrorDto> handleAddUserException(AddUserException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new AppUserErrorDto(e.getMessage()));
    }
}
