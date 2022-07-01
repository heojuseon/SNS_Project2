package com.example.sns_project2.youtubeAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeRequest {
    private final static String YOUTUBE_URL = "https://www.googleapis.com"; // 서버 URL
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null) {

//            Gson gson = new GsonBuilder().setLenient().create(); // JSON 응답을 객체로 변환하기 위해 필요

            retrofit = new Retrofit.Builder()
                    .baseUrl(YOUTUBE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
