package com.example.sns_project2.sns_data;


import java.io.Serializable;

//Intent로 객체를 전달할때는 implements Serializable 사용해야한다.
public class Sns implements Serializable {
    //Sns클래스는 Sns_board클래스에서 추가한 데이터들의 목록을 보여주는 역할을 하는 클래스

    private int sid;
    private String title;
    private String img;
    private String date;
    private String content;

    public Sns(int sid, String title, String img, String date, String content) {
        this.sid = sid;
        this.title = title;
        this.img = img;
        this.date = date;
        this.content = content;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

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
