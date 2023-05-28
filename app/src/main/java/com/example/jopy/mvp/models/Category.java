package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {
    private String title;
    private boolean clicked;
    @SerializedName("word")
    private String word;
    @SerializedName("tag_type")
    private String tagType;
    @SerializedName("tags")
    private List<String> categories;
    private List<Post> posts;
    public Category() {
    }
    public Category(String title, boolean clicked) {
        this.title = title;
        this.clicked = clicked;
    }

    public Category(String word, String tagType) {
        this.word = word;
        this.tagType = tagType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
