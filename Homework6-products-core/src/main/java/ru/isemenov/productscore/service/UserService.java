package ru.isemenov.productscore.service;

import org.springframework.stereotype.Service;
import ru.isemenov.productscore.dto.UserInfoDto;
import ru.isemenov.productscore.model.User;
import ru.isemenov.productscore.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    public UserService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public UserInfoDto getUserInfoById(Long userId) {
        User user = findById(userId);
        Integer balance = accountService.getBalance(user.getAccount());
        return new UserInfoDto(
                userId, user.getUsername(), user.getAccount(), balance
        );
    }

    private User findById(Long userId) {
        return userRepository.findById(userId);
    }
}
