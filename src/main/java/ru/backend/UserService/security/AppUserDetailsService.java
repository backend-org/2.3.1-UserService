package ru.backend.UserService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.repository.user.AppUserRepository;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findByUserName(username);

        if(appUser.isEmpty()){
            throw new UsernameNotFoundException("Пользователь с таким юзернеймом не найден");
        }

        return new AppUserDetails(appUser.get());
    }
}
