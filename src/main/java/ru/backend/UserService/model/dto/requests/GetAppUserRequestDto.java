package ru.backend.UserService.model.dto.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GetAppUserRequestDto {

    @Min(value = 0, message = "Id пользователя должен быть положительным")
    private long id;
}
