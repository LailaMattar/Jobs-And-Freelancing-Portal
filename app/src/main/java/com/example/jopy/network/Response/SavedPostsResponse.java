package com.example.jopy.network.Response;

import com.example.jopy.mvp.models.Post;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SavedPostsResponse {

    @SerializedName("favorites")
    protected List<Post> favorites;

    public SavedPostsResponse() {
    }

    @Override
    public String toString() {
        return "SavedPostsResponse{" +
                "favorites=" + favorites +
                '}';
    }

    public List<Post> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Post> favorites) {
        this.favorites = favorites;
    }
}
