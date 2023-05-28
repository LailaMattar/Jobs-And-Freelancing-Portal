package com.example.jopy.network.Response;

import com.example.jopy.mvp.models.Applicant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplicantsResponse {

    @SerializedName("applicants")
    protected List<Applicant> applicants;


    public ApplicantsResponse() {
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }

    @Override
    public String toString() {
        return "ApplicantsResponse{" +
                "applicants=" + applicants.get(2).toString() +
                '}';
    }
}
