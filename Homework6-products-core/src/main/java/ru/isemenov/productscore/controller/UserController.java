package ru.isemenov.productscore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isemenov.productscore.dto.UserInfoDto;
import ru.isemenov.productscore.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserInfoDto getUserInfoById(@PathVariable Long id) {
        return userService.getUserInfoById(id);
    }
}
