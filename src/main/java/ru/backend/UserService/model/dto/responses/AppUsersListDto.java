package ru.backend.UserService.model.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AppUsersListDto {
    private List<AppUserDto> users;
}
