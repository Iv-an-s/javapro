package ru.isemenov.productscore.service;

import org.springframework.stereotype.Service;
import ru.isemenov.productscore.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Integer getBalance(String account) {
        return accountRepository.getBalance(account);
    }
}
