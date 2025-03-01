package ru.backend.UserService.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.UserService.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUserName(String userName);
}
