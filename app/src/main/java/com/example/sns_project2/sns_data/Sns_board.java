package com.example.sns_project2.sns_data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sns_board implements Serializable{
    //Sns_board 클래스는 서버에 데이터 추가하기 위한 클래스

    @SerializedName("title")
    String title;

    @SerializedName("img")
    String img;

    @SerializedName("date")
    String date;

    @SerializedName("content")
    String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
