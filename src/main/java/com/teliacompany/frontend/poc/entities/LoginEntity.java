package com.teliacompany.frontend.poc.entities;

import javax.json.bind.annotation.JsonbProperty;

/**
 * This is a loginEntity, it mirrors a LoginEntity class in backend1 which holds a users information in Jsonb properties
 */
public class LoginEntity {

    // FRONTEND login entity SHOULD contain loginToken

    @JsonbProperty("status")
    private String status;
    @JsonbProperty("username")
    private String username;
    @JsonbProperty("password")
    private String password;
    @JsonbProperty("loginToken") // "loginEntity" holds the token created when they were logged in.
    private String loginToken;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

}