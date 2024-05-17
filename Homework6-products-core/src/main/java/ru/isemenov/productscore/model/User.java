package ru.isemenov.productscore.model;

public class User {
    private Long id;
    private String username;
    private String account;

    public User(Long id, String username, String account) {
        this.id = id;
        this.username = username;
        this.account = account;
    }

    public User() {
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
}
