package ru.backend.UserService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.backend.UserService.model.User;
import ru.backend.UserService.services.user.UserService;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users/users_table";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/user_page";
    }

    @GetMapping("/new")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/add_user_form";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }


    @GetMapping("/fillUsersDb")
    public String fillUsersTable(Model model) {
        userService.add(new User("name1", "surname1", "email1", "address1"));
        userService.add(new User("name2", "surname2", "email2", "address2"));
        userService.add(new User("name3", "surname3", "email3", "address3"));
        userService.add(new User("name4", "surname4", "email4", "address4"));
        userService.add(new User("name5", "surname5", "email5", "address5"));
        return "users/users_table";
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<String> handleBadRequest(UserNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }

}