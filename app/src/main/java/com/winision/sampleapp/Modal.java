package com.winision.sampleapp;

import com.google.gson.annotations.SerializedName;

public class Modal {
    @SerializedName("email")
    private String title;
    @SerializedName("name")
    private String body;

    public Modal(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
