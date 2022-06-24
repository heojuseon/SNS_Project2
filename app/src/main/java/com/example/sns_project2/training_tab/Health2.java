package com.example.sns_project2.training_tab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sns_project2.R;
import com.example.sns_project2.youtubeAPI.ItemsBean;
import com.example.sns_project2.youtubeAPI.YoutubeAPI;
import com.example.sns_project2.youtubeAPI.YoutubeRequest;
import com.example.sns_project2.youtubeAPI.YoutubeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Health2 extends Fragment {

    Retrofit retrofit;
    YoutubeService youtubeService;


    private String MY_KEY = "AIzaSyCzmawqBqx01jL2nzmdCH4urzpW5ygclmM"; // API KEY
    private String Q = "헬스"; // 유튜브 검색값
    private int MAX_RESULTS = 10; // 받아올 유튜브 리스트의 최대값


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_health2, container, false);

        retrofit = YoutubeRequest.getClient();
        youtubeService = retrofit.create(YoutubeService.class);


        getYoutubeList();

        return rootView;
    }

    private void getYoutubeList() {
        Log.d("Youtube_apiTest ","getYoutubeList");
        youtubeService = retrofit.create(YoutubeService.class);

        youtubeService.getList(MY_KEY,"snippet", Q, "video", MAX_RESULTS, "viewCount").enqueue(new Callback<YoutubeAPI>() {
            @Override
            public void onResponse(Call<YoutubeAPI> call, Response<YoutubeAPI> response) {//성공시
                Log.d("YOUTUBE", "Response");

                YoutubeAPI data = response.body(); // 받아온 데이터를 변수에 저장

                List<ItemsBean> youtubeList = data.getItems(); // 변수에서 아이템 리스트들을 받아온다.
                for (int i = 0; i < youtubeList.size(); i++){

                    ItemsBean itemsBean = youtubeList.get(i);

                    Log.d("Youtube_apiTest",itemsBean.toString());
                    Log.d("Youtube_apiTest",itemsBean.getSnippet().getTitle());
                    Log.d("Youtube_apiTest",itemsBean.getSnippet().getThumbnails().getMedium().getUrl());

                }
            }

            @Override
            public void onFailure(Call<YoutubeAPI> call, Throwable t) {

            }
        });
    }
}