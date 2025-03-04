package ru.backend.UserService.services.user;



import ru.backend.UserService.model.AppUser;

import java.util.List;

public interface AppUserService {
    void add(AppUser user);

    void add(AppUser user, boolean isAdmin);

    List<AppUser> getUsersList();

    AppUser getUserById(Long id);

    void edit(AppUser newUser);

    void delete(Long id);

}
