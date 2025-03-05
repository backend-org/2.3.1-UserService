package ru.backend.UserService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.services.user.AppUserService;

@Controller
@RequestMapping("users")
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        AppUser user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/user_page";
    }
}
