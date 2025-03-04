package ru.backend.UserService.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.model.Role;
import ru.backend.UserService.repository.AppUserRepository;
import ru.backend.UserService.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Не существует роли с именем " + name));
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }
}
