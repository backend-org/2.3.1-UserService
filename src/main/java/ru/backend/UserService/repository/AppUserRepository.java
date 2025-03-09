package ru.backend.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.UserService.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
