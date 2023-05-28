package com.example.jopy.network.Response;


import com.google.gson.annotations.SerializedName;

public class Response<T> {
    @SerializedName("token")
    public String token ;
    @SerializedName("user")
    protected T user ;
    @SerializedName("message")
    public String message ;
    public Response(String token, T user) {
        this.token = token;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response(String token) {
        this.token = token;
    }

    public Response(T user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }
}
