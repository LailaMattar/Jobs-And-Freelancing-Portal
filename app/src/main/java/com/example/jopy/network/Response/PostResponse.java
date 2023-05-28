package com.example.jopy.network.Response;

import com.google.gson.annotations.SerializedName;

public class PostResponse <T> {
    @SerializedName("post")
    protected T post;

    @SerializedName("message")
    protected String message;

    public PostResponse(T post) {
        this.post = post;
    }

    public T getPost() {
        return post;
    }

    public void setPost(T post) {
        this.post = post;

    }

    public String getMessage() {
        return message;
    }
}