package ru.backend.UserService.dao.user;

import ru.backend.UserService.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    User getUserById(int id);

}