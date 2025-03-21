package ru.backend.UserService.model.dto.requests;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteAppUserRequestDto {
    @Min(value = 0, message = "Id удаляемого пользователя должен быть положительным")
    private Long id;
}
