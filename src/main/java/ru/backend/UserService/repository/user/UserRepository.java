package ru.backend.UserService.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.UserService.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
