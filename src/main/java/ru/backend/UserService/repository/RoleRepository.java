package ru.backend.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
