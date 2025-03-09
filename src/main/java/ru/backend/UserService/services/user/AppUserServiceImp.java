package ru.backend.UserService.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.UserService.model.Role;
import ru.backend.UserService.repository.AppUserRepository;
import ru.backend.UserService.model.AppUser;

import java.util.List;
import java.util.Set;


@Service
@Transactional(readOnly = true)
public class AppUserServiceImp implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void add(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
    }

    @Transactional
    @Override
    public void add(AppUser user, boolean isAdmin) {
        if (appUserRepository.existsByUserName(user.getUserName())) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует.");
        }
        if(isAdmin){
            user.setRoles(roleService.getAllRoles());
        }
        else{
            user.setRoles(Set.of(roleService.getRoleByName("ROLE_USER")));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
    }

    @Override
    public List<AppUser> getUsersList() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void edit(AppUser newUser) {
        appUserRepository.save(newUser);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        appUserRepository.deleteById(id);
    }

}
