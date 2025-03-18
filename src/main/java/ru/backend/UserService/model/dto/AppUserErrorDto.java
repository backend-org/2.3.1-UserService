package ru.backend.UserService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppUserErrorDto {
    private String errorMsg;
}
