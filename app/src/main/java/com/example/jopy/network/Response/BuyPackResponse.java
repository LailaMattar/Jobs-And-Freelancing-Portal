package com.example.jopy.network.Response;

import com.google.gson.annotations.SerializedName;

public class BuyPackResponse
{

    @SerializedName("message")
    protected String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BuyPackResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
