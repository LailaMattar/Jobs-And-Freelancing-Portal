package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notification {
    @SerializedName("your_requests")
    private List<Applicant> applicants1;
    @SerializedName("your_posts_updates")
    private List<Applicant> applicants2;

    public List<Applicant> getApplicants1() {
        return applicants1;
    }

    public void setApplicants1(List<Applicant> applicants1) {
        this.applicants1 = applicants1;
    }

    public List<Applicant> getApplicants2() {
        return applicants2;
    }

    public void setApplicants2(List<Applicant> applicants2) {
        this.applicants2 = applicants2;
    }
}
