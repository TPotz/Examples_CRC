package com.example.web.demo.modeli;

public class User {
    public String userName;
    public String password;
    public String role;
    public Integer locked;

    public User(String userName, String password, String role, Integer locked) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.locked = locked;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }
}
