package com.sunc.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Description:
 * Date: 2018-08-07 09:21
 * Author: suncheng
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return code == 200;
    }
}
