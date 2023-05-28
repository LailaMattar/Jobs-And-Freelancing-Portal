package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("id")
    private int id;
    @SerializedName("post_id")
    private int post_id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("rating")
    private int rating;
    @SerializedName("rating_text")
    private String  rating_text;
    @SerializedName("created_at")
    private String  created_at;
   @SerializedName("updated_at")
    private String  updated_at;

    public Rating() {
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", rating=" + rating +
                ", rating_text='" + rating_text + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRating_text() {
        return rating_text;
    }

    public void setRating_text(String rating_text) {
        this.rating_text = rating_text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
