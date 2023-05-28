package com.example.jopy.network.Response;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse<T> {
    @SerializedName("created user")
    protected T user;

    @SerializedName("message")
    protected String message;

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
