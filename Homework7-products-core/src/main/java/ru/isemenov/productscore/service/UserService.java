package ru.isemenov.productscore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isemenov.productscore.dto.UserInfoDto;
import ru.isemenov.productscore.entity.User;
import ru.isemenov.productscore.exception.ResourceNotFoundException;
import ru.isemenov.productscore.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserInfoDto getUserInfoById(Long userId) {
        User user = findById(userId);
        return new UserInfoDto(
                userId,
                user.getUsername(),
                user.getAccount().getAccountName(),
                user.getAccount().getBalance()
        );
    }

    public User findById(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Пользователь с id = %d не найден в БД", userId)));
    }
}
