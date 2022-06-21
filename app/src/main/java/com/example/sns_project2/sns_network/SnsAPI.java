package com.example.sns_project2.sns_network;

import com.example.sns_project2.sns_data.Sns;
import com.example.sns_project2.sns_data.Sns_board;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SnsAPI {

    @GET("sns") //전체 목록
    Call<ArrayList<Sns>> getSnsList();
//    @GET("sns") //전체 목록
//    Call<ArrayList<Sns_board>> getSnsList();

    @POST("sns") //등록
    Call<Sns_board> addSns(@Body Sns_board post);

    @DELETE("sns/{sid}")
    Call<Sns> deleteSns(@Path("sid") int sid);

//    @GET("sns/{sid}") //상세 목록
//    Call<Sns> getSns(@Path("sid") int sid);
//
//    @PATCH("sns/{sid}")
//    Call<Void> updateSns(@Path("sid") int sid, @Body Sns sns);
//
//    @DELETE("sns/{sid}")
//    Call<Void> deleteSns(@Path("sid") int sid);
}
