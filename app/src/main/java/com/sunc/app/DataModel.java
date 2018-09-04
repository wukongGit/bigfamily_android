package com.sunc.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Description:
 * Date: 2018-08-07 09:24
 * Author: suncheng
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataModel<T> extends BaseModel {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
