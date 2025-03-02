package ru.backend.UserService.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.services.user.AppUserService;

@Controller
public class AuthController {

    private AppUserService appUserService;

    @Autowired
    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login.html";
    }

    @GetMapping("/register")
    public String showRegistrationForm(@ModelAttribute("user") AppUser appuser) {
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid AppUser appuser) {
        appUserService.add(appuser);
        return "redirect:/login";
    }
}
