package com.teliacompany.frontend.poc.entities;

import javax.json.bind.annotation.JsonbProperty;

public class UserEntity {

    @JsonbProperty
    private String username;
    @JsonbProperty
    private String password;
    @JsonbProperty
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