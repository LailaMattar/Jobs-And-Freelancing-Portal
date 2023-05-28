package com.example.jopy.mvp.models;

import java.util.List;

public class PostService {
    private String title ,time ,accountName ;
    private int cost ,accountImg ,postImg;
    private List<Tag> tags;

    public PostService(String title, String time, String accountName, int cost, int accountImg, int postImg, List<Tag> tags) {
        this.title = title;
        this.time = time;
        this.accountName = accountName;
        this.cost = cost;
        this.accountImg = accountImg;
        this.postImg = postImg;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(int accountImg) {
        this.accountImg = accountImg;
    }

    public int getPostImg() {
        return postImg;
    }

    public void setPostImg(int postImg) {
        this.postImg = postImg;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
