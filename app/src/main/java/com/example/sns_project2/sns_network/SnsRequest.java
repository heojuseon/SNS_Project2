package com.example.sns_project2.sns_network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SnsRequest {

    private final static String BASE_URL = "http://10.0.2.2:8080/api/"; // 서버 URL
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
