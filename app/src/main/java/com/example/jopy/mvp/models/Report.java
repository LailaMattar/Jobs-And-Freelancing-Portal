package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

public class Report {

    @SerializedName("post_id")
    private String post_id;
    @SerializedName("reason")
    private String reason;


    public Report() {

    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
