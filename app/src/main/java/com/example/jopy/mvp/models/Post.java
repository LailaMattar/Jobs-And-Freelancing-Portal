package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.List;

public class Post {
    @SerializedName("image")
    private String image;
    @SerializedName("post_type")
    private String post_type;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("about")
    private String about;
    @SerializedName("money")
    private String money;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("categories")
    private List<String> categories;
    @SerializedName("required_experience")
    private String requiredExperience;
    @SerializedName("employment_type")
    private String employmentType;
    @SerializedName("location")
    private String location;
    @SerializedName("servise_time")
    private String serviceTime;
    @SerializedName("id")
    private int id;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("type_id")
    private int type_id;
    @SerializedName("is_waiting")
    private int isWaiting;
    @SerializedName("modified")
    private int modified;
    @SerializedName("deleted")
    private int deleted;
    @SerializedName("total_rating")
    private int total_rating;
    @SerializedName("accepted")
    private int accepted;
    @SerializedName("ratings")
    private List<Rating> ratings;
    @SerializedName("city")
    private String city;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("country")
    private String country;
    @SerializedName("user")
    private User user;
    @SerializedName("number_of_applicants")
    private int number_of_applicants;
    @SerializedName("post_id")
    private String post_id;

    public int getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(int total_rating) {
        this.total_rating = total_rating;
    }


    public Post() {
    }

    public Post(String type, String title, String about, String money, List<String> tags, List<String> categories, String requiredExperience, String employmentType, String location, String serviceTime) {
        this.post_type = type;
        this.title = title;
        this.about = about;
        this.money = money;
        this.tags = tags;
        this.categories = categories;
        this.requiredExperience = requiredExperience;
        this.employmentType = employmentType;
        this.location = location;
        this.serviceTime = serviceTime;
    }


    public Post(String post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getNumber_of_applicants() {
        return number_of_applicants;
    }

    public void setNumber_of_applicants(int number_of_applicants) {
        this.number_of_applicants = number_of_applicants;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getRequiredExperience() {
        return requiredExperience;
    }

    public void setRequiredExperience(String requiredExperience) {
        this.requiredExperience = requiredExperience;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getIsWaiting() {
        return isWaiting;
    }

    public void setIsWaiting(int isWaiting) {
        this.isWaiting = isWaiting;
    }

    public int getModified() {
        return modified;
    }

    public void setModified(int modified) {
        this.modified = modified;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Post{" +
                "image='" + image + '\'' +
                ", post_type='" + post_type + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", about='" + about + '\'' +
                ", money='" + money + '\'' +
                ", tags=" + tags +
                ", categories=" + categories +
                ", requiredExperience='" + requiredExperience + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", location='" + location + '\'' +
                ", serviceTime='" + serviceTime + '\'' +
                ", id=" + id +
                ", userId=" + userId +
                ", type_id=" + type_id +
                ", isWaiting=" + isWaiting +
                ", modified=" + modified +
                ", deleted=" + deleted +
                ", accepted=" + accepted +
                ", ratings=" + ratings +
                ", city='" + city + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", created_at='" + created_at + '\'' +
                ", country='" + country + '\'' +
                ", user=" + user +
                '}';
    }
}