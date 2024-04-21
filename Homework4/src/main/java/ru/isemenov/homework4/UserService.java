package ru.isemenov.homework4;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser(String username) {
        userDao.saveUser(username);
    }

    public void saveUser(User user) {
        userDao.saveUser(user.getUsername());
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userDao.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User with id = " + id + " is not present"));
    }

    public User findByUsername(String username) {
        Optional<User> optionalUser = userDao.findByUsername(username);
        return optionalUser.orElseThrow(() -> new RuntimeException("User with username = " + username + " is not present"));
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User deleteById(Long id) {
        Optional<User> optionalUser = userDao.deleteById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("There is no such user in database with id = " + id));
    }

    public User deleteByUsername(String username) {
        Optional<User> optionalUser = userDao.deleteByUsername(username);
        return optionalUser.orElseThrow(() -> new RuntimeException("There is no such user in database with username = " + username));
    }

    public void deleteAll(){
        userDao.deleteAll();
    }
}
