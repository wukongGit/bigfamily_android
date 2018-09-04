package com.sunc.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Description:
 * Date: 2018-08-07 15:57
 * Author: suncheng
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginData {
    User user;
    String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
