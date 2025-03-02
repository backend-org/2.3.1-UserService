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
    AppUserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "users/users_table";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        AppUser user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/user_page";
    }

    @GetMapping("/new")
    public String addUserForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "users/add_user_form";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") AppUser user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit_user";
    }

    @PatchMapping("/edit")
    public String editUser(@ModelAttribute("user") AppUser user){
        var oldUserData = userService.getUserById(user.getId());
        user.setUserName(oldUserData.getUserName());
        user.setPassword(oldUserData.getPassword());
        userService.edit(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }

}
