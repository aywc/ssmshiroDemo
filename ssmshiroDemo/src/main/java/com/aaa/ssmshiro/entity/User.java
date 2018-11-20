package com.aaa.ssmshiro.entity;

/**
 * className:User
 * discriptoin:
 * author:邢博
 * createTime:2018-11-15 22:00
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String passwordSalt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
