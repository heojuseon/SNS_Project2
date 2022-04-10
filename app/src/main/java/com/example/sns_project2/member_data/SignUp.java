package com.example.sns_project2.member_data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SignUp implements Serializable {

    @SerializedName("userId")
    String userId;

    @SerializedName("userPW")
    String userPW;

    @SerializedName("userName")
    String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
