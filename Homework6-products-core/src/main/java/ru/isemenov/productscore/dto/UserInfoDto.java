package ru.isemenov.productscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("account")
    private String account;
    @JsonProperty("balance")
    private Integer balance;

    public UserInfoDto(Long id, String username, String account, Integer balance) {
        this.id = id;
        this.username = username;
        this.account = account;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", account='" + account + '\'' +
                ", balance=" + balance +
                '}';
    }
}
