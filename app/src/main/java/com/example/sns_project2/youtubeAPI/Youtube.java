package com.example.sns_project2.youtubeAPI;

public class Youtube {

    private String youtube_img;
    private String youtube_title;

    public Youtube(String youtube_img, String youtube_title) {
        this.youtube_img = youtube_img;
        this.youtube_title = youtube_title;
    }

    public String getYoutube_img() {
        return youtube_img;
    }

    public void setYoutube_img(String youtube_img) {
        this.youtube_img = youtube_img;
    }

    public String getYoutube_title() {
        return youtube_title;
    }

    public void setYoutube_title(String youtube_title) {
        this.youtube_title = youtube_title;
    }
}
