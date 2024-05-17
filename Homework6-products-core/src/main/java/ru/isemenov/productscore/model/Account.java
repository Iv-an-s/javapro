package ru.isemenov.productscore.model;

public class Account {
    private Long id;
    private String account;
    private Integer balance;

    public Account(Long id, String account, Integer balance) {
        this.id = id;
        this.account = account;
        this.balance = balance;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
