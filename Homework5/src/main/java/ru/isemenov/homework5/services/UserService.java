package ru.isemenov.homework5.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isemenov.homework5.models.User;
import ru.isemenov.homework5.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User save(User user){
        user.setId(repository.save(user.getUsername()));
        return user;
    }
}
