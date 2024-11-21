package ru.backend.UserService.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.UserService.repository.user.UserRepository;
import ru.backend.UserService.model.User;

import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void edit(User newUser) {
        userRepository.save(newUser);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

}
