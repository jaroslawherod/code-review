package com.softwareag.code.review.model;

public class UserDetails {
    private String username;
    private String email;
    private String password;

    public UserDetails(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasword() {
        return password;
    }
}
