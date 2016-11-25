package com.qks.beautyexchange.api.common;

import com.google.gson.Gson;

/**
 * 服务器返回Json公共格式
 * Created by admin on 2016/3/23.
 */
public class HttpResult<T> {

    private int status;

    private String message;

    private T results;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getResults() {
        return results;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
