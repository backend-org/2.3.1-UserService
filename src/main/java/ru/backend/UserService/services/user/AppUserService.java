package ru.backend.UserService.services.user;



import ru.backend.UserService.model.AppUser;

import java.util.List;

public interface AppUserService {
    void add(AppUser user);

    List<AppUser> getUsersList();

    AppUser getUserById(int id);

    void edit(AppUser newUser);

    void delete(int id);

}
