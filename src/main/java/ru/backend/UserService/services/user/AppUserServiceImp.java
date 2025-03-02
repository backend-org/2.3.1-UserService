package ru.backend.UserService.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.UserService.repository.user.AppUserRepository;
import ru.backend.UserService.model.AppUser;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class AppUserServiceImp implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Transactional
    @Override
    public void add(AppUser user) {
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
