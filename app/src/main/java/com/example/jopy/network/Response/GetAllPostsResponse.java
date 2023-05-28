package com.example.jopy.network.Response;

import com.google.gson.annotations.SerializedName;


public class GetAllPostsResponse<T> {
    @SerializedName("posts")
    protected T posts;

    public GetAllPostsResponse(T posts) {
        this.posts = posts;
    }

    public T getPosts() {
        return posts;
    }

    public void setPosts(T posts) {
        this.posts = posts;
    }
}
