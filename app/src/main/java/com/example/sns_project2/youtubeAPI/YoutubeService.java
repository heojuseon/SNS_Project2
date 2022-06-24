package com.example.sns_project2.youtubeAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {

    @GET("/youtube/v3/search")
    Call<YoutubeAPI> getList(
            @Query("key") String key,
            @Query("part") String part,
            @Query("q") String q,
            @Query("type") String type,
            @Query("maxResults") int maxResults,
            @Query("order") String order

            //여기다가 파람 order 추가해서 조회수 순으로 보이게 하기
    );
}
