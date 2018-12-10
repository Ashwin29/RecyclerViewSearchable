package com.winision.sampleapp.Modals;

import com.google.gson.annotations.SerializedName;

public class VideoModal {

    @SerializedName("title")
    private String title;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    public VideoModal(String title, String thumbnailUrl) {

        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
