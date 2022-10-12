package com.teliacompany.frontend.poc.entities;

import javax.json.bind.annotation.JsonbProperty;

public class UserEntity {

    @JsonbProperty("username")
    private String username;
    @JsonbProperty("password")
    private String password;
    @JsonbProperty("email")
    private String email;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}