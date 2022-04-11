package com.example.sns_project2.network;

import com.example.sns_project2.member_data.Login;
import com.example.sns_project2.member_data.SignUp;
import com.example.sns_project2.member_data.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignUpAPI {

    @GET("user") //가입 목록
    Call<ArrayList<SignUp>> getSignUpList();

    @POST("user/signup") //회원 가입
    Call<Void> addSignUp(@Body SignUp post);

//    @POST("user/signin") //로그인
//    Call<Void> addLogin(@Body Login post);

//    @FormUrlEncoded
//    @POST("user/signin") //로그인
//    Call<Void> addLogin(@Field("id") String id, @Field("pw") String pw);

    @GET("user/signin")//로그인
    Call<User> addLogin(@Query("id") String id, @Query("pw") String pw);



//    @GET("sns") //전체 목록
//    Call<ArrayList<Sns>> getSnsList();
//
//    @POST("sns") //상세 목록
//    Call<Void> addSns(@Body Sns post);
//
//    @GET("sns/{sid}")
//    Call<Sns> getSns(@Path("sid") int sid);
//
//    @PATCH("sns/{sid}")
//    Call<Void> updateSns(@Path("sid") int sid, @Body Sns sns);
//
//    @DELETE("sns/{sid}")
//    Call<Void> deleteSns(@Path("sid") int sid);
}
