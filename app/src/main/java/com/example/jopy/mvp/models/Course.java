package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("course_title")
    private String course;

    public Course(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
