package ru.backend.UserService.model.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppUserErrorResponseDto {
    private String errorMsg;
}
