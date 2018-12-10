package com.winision.sampleapp.Modals;

import com.google.gson.annotations.SerializedName;

public class Modal {
    @SerializedName("email")
    private String title;
    @SerializedName("name")
    private String name;

    public Modal(String title, String body) {
        this.title = title;
        this.name = body;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String body) {
        this.name = name;
    }
}
