package ru.backend.UserService.services.user;



import ru.backend.UserService.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User getUserById(int id);

    void edit(int id, User newUser);

    void delete(int id);

}
