package com.example.jopy.network.Response;

import com.example.jopy.mvp.models.Notification;
import com.google.gson.annotations.SerializedName;

public class NotificationResponse {
    @SerializedName("notifications")
    Notification notification;

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
