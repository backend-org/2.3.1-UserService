package ru.backend.UserService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.services.user.AppUserService;
import ru.backend.UserService.services.user.RoleService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/users")
public class AdminController {

    private final AppUserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(AppUserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "users/users_table";
    }

    @GetMapping("/new")
    public String addUserForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "users/add_user_form";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") AppUser user,
                          @RequestParam(name = "adminParam", defaultValue = "false") Boolean isAdmin) {
        userService.add(user, isAdmin);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "users/edit_user";
    }

    @PatchMapping("/edit")
    public String editUser(@ModelAttribute("user") AppUser user,
                           @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        var oldUserData = userService.getUserById(user.getId());
        user.setUserName(oldUserData.getUserName());
        user.setPassword(oldUserData.getPassword());
        userService.edit(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

}
