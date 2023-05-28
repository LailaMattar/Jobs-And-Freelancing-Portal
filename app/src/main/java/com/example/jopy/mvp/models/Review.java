package com.example.jopy.mvp.models;

public class Review {
    private int accountImg;
    private String accountName, time ,review;

    public Review(int accountImg, String accountName, String time, String review) {
        this.accountImg = accountImg;
        this.accountName = accountName;
        this.time = time;
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(int accountImg) {
        this.accountImg = accountImg;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
