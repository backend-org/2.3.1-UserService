package ru.backend.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.backend.UserService.model.dto.errors.AppUserErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<AppUserErrorResponseDto> handleAddUserException(UserException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new AppUserErrorResponseDto(e.getMessage()));
    }
}
