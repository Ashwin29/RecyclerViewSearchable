package com.winision.sampleapp.Modals;

import com.google.gson.annotations.SerializedName;

public class ModelModal {

    @SerializedName("title")
    private String title;

    public ModelModal(String title) {

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
