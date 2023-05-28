package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

public class Package1 {

    @SerializedName("id")
    private int id;

    @SerializedName("package_for")
    private String package_for;

    @SerializedName("price")
    private int price;

    @SerializedName("posts_number")
    private int posts_number;

    @SerializedName("availble_for")
    private String availble_for;

    public Package1(){


    }
    public Package1(int id, String package_for, int price, int posts_number, String availble_for) {
        this.id = id;
        this.package_for = package_for;
        this.price = price;
        this.posts_number = posts_number;
        this.availble_for = availble_for;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackage_for() {
        return package_for;
    }

    public void setPackage_for(String package_for) {
        this.package_for = package_for;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPosts_number() {
        return posts_number;
    }

    public void setPosts_number(int posts_number) {
        this.posts_number = posts_number;
    }

    public String getAvailble_for() {
        return availble_for;
    }

    public void setAvailble_for(String availble_for) {
        this.availble_for = availble_for;
    }

    @Override
    public String toString() {
        return "Package1{" +
                "id=" + id +
                ", package_for='" + package_for + '\'' +
                ", price=" + price +
                ", posts_number=" + posts_number +
                ", availble_for='" + availble_for + '\'' +
                '}';
    }
}
