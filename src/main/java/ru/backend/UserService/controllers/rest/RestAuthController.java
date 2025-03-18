package ru.backend.UserService.controllers.rest;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.backend.UserService.exceptions.AddUserException;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.model.dto.RegisterAppUserDto;
import ru.backend.UserService.services.user.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAuthController {

    private final AppUserService appUserService;
    private final ModelMapper modelMapper;

    public RestAuthController(AppUserService appUserService, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody @Valid RegisterAppUserDto registerAppUserDto,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + " : " + error.getDefaultMessage())
                    .toList();
            throw new AddUserException(String.join("; ", errors));
        }

        AppUser newUser = modelMapper.map(registerAppUserDto, AppUser.class);
        appUserService.add(newUser);
        return ResponseEntity.ok().build();
    }
}
