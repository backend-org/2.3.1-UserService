package ru.backend.UserService.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ru.backend.UserService.model.Role;

import java.util.Set;

@Getter
@Setter
public class RegisterAppUserDto {

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(min = 3, max = 20, message = "Имя пользователя должно содержать от 3 до 20 символов")
    private String userName;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;

    @NotBlank(message = "Имя не должен быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия не должна быть пустой")
    private String lastName;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Адрес не должен быть пустым")
    private String address;

    @NotEmpty(message = "У пользователя должна быть хотя бы одна роль")
    private Set<Role> roles;

}
