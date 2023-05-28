package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

public class Applicant2 {

    @SerializedName("applicant_id")
    private String applicant_id;


    public String getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(String applicant_id) {
        this.applicant_id = applicant_id;
    }

    @Override
    public String toString() {
        return "Applicant2{" +
                "applicant_id=" + applicant_id +
                '}';
    }
}
