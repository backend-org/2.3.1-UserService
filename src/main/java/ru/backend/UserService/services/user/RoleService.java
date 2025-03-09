package ru.backend.UserService.services.user;


import ru.backend.UserService.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleByName(String name);
    Set<Role> getAllRoles();
    Set<Role> getRolesByIds(List<Long> roleIds);
}
