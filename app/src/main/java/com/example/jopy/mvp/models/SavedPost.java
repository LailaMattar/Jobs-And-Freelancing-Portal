package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

public class SavedPost {

    @SerializedName("post_id")
    private int post_id;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public SavedPost() {
    }
}
