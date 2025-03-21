package ru.backend.UserService.controllers.rest;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.backend.UserService.exceptions.GetUserException;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.model.dto.responses.AppUserDto;
import ru.backend.UserService.model.dto.requests.GetAppUserRequestDto;
import ru.backend.UserService.services.user.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RestAppUserController {

    @Autowired
    private AppUserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<AppUserDto> getUserById(@RequestBody @Valid GetAppUserRequestDto getAppUserRequestDto,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + " : " + error.getDefaultMessage())
                    .toList();
            throw new GetUserException(String.join("; ", errors));
        }

        AppUser user = userService.getUserById(getAppUserRequestDto.getId());
        return new ResponseEntity<>(modelMapper.map(user, AppUserDto.class), HttpStatus.OK);
    }
}
