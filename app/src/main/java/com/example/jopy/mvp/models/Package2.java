package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

public class Package2 {


    @SerializedName("package_id")
    private int package_id;


    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public Package2() {
    }
    public Package2(int package_id) {

        this.package_id = package_id;
    }

    @Override
    public String toString() {
        return "Package2{" +
                "package_id=" + package_id +
                '}';
    }
}
