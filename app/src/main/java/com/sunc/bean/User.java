package com.sunc.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Description:
 * Date: 2018-08-07 15:57
 * Author: suncheng
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    Long id;
    String username;
    String password;
    String image;
    Long memberId;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
