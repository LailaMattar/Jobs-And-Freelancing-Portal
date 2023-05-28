package com.example.jopy.mvp.models;

public class Comment {
    private String userCM;
    private String userComment;
    private String timeCM;
    private float ratingBarCM;

    public Comment(String userCM, String userComment, String timeCM, float ratingBarCM) {
        this.userCM = userCM;
        this.userComment = userComment;
        this.timeCM = timeCM;
        this.ratingBarCM = ratingBarCM;
    }

    public String getUserCM() {
        return userCM;
    }

    public void setUserCM(String userCM) {
        this.userCM = userCM;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getTimeCM() {
        return timeCM;
    }

    public void setTimeCM(String timeCM) {
        this.timeCM = timeCM;
    }

    public float getRatingBarCM() {
        return ratingBarCM;
    }

    public void setRatingBarCM(float ratingBarCM) {
        this.ratingBarCM = ratingBarCM;
    }
}
