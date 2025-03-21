package ru.backend.UserService.controllers.rest;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.backend.UserService.exceptions.DeleteUserException;
import ru.backend.UserService.exceptions.EditUserException;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.model.dto.requests.DeleteAppUserRequestDto;
import ru.backend.UserService.model.dto.requests.EditAppUserRequestDto;
import ru.backend.UserService.model.dto.responses.AppUserDto;
import ru.backend.UserService.model.dto.responses.AppUsersListDto;
import ru.backend.UserService.services.user.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RestAdminController {

    @Autowired
    private AppUserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Все методы REST контрлллера POST, потому что в таком формате принята передача у меня на работе
    @GetMapping()
    public ResponseEntity<AppUsersListDto> getAllUsers() {
        List<AppUserDto> users = userService.getUsersList().stream()
                .map(user -> modelMapper.map(user, AppUserDto.class))
                .toList();
        AppUsersListDto usersListDto = new AppUsersListDto(users);

        return new ResponseEntity<>(usersListDto, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editUser(@RequestBody @Valid EditAppUserRequestDto editedUserDto,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + " : " + error.getDefaultMessage())
                    .toList();
            throw new EditUserException(String.join("; ", errors));
        }

        var editedUser = modelMapper.map(editedUserDto, AppUser.class);
        editedUser.setPassword(passwordEncoder.encode(editedUser.getPassword()));
        userService.edit(editedUser);

        return new ResponseEntity<>("Edited Successfully", HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody @Valid DeleteAppUserRequestDto deleteAppUserRequestDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + " : " + error.getDefaultMessage())
                    .toList();
            throw new DeleteUserException(String.join("; ", errors));
        }

        userService.delete(deleteAppUserRequestDto.getId());
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
