package ru.backend.UserService.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.services.user.AppUserService;
import ru.backend.UserService.services.user.RoleService;

@Controller
public class AuthController {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthController(AppUserService appUserService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Autowired


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login.html";
    }

    @GetMapping("/register")
    public String showRegistrationForm(@ModelAttribute("user") AppUser appuser) {
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid AppUser appuser,
                               @RequestParam(name = "adminParam", defaultValue = "false") Boolean isAdmin) {
        appUserService.add(appuser, isAdmin);
        return "redirect:/login";
    }
}
