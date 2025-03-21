package ru.backend.UserService.model.dto.responses;

import lombok.Getter;
import lombok.Setter;
import ru.backend.UserService.model.Role;

import java.util.Set;

@Getter
@Setter
public class AppUserDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Set<Role> roles;
}
