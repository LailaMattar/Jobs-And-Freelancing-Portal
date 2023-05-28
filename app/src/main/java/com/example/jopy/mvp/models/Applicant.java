package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

public class Applicant
{
    @SerializedName("id")
    private int id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("post_id")
    private int post_id;
    @SerializedName("seen_by_owner")
    private int seen_by_owner;
    @SerializedName("seen_by_user")
    private int seen_by_user;
    @SerializedName("application_status")
    private String application_status;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("post")
    private Post post;
    @SerializedName("user")
    private User user;
    private int notificationType;//1 for my requests and 2 for my updates

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", post_id=" + post_id +
                ", seen_by_owner=" + seen_by_owner +
                ", seen_by_user=" + seen_by_user +
                ", application_status='" + application_status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public int getSeen_by_owner() {
        return seen_by_owner;
    }

    public void setSeen_by_owner(int seen_by_owner) {
        this.seen_by_owner = seen_by_owner;
    }

    public int getSeen_by_user() {
        return seen_by_user;
    }

    public void setSeen_by_user(int seen_by_user) {
        this.seen_by_user = seen_by_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Applicant() {
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

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(int notificationType) {
        this.notificationType = notificationType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
