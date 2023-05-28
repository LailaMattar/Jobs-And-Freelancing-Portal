package com.example.jopy.network.Response;

import com.example.jopy.mvp.models.Applicant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AcceptedBeforeResponse {

    @SerializedName("accepted")
    protected boolean accepted;

    public AcceptedBeforeResponse() {
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
