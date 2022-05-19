package com.example.sns_project2.board_tab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sns_project2.R;
import com.example.sns_project2.sns_data.Sns;
import com.example.sns_project2.sns_network.SnsAPI;
import com.example.sns_project2.sns_network.SnsRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Board extends Fragment {
    ArrayList<Sns> snslist;
    Retrofit retrofit;
    SnsAPI snsAPI;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);

        snslist = new ArrayList<>();
        retrofit = SnsRequest.getClient();
        snsAPI = retrofit.create(SnsAPI.class);

        getSnsList();

        return rootView;
    }

    private void getSnsList() {

        Log.d("apiTest ","getSnsList");
        snsAPI = retrofit.create(SnsAPI.class);

        snsAPI.getSnsList().enqueue(new Callback<ArrayList<Sns>>() {
            @Override
            public void onResponse(Call<ArrayList<Sns>> call, Response<ArrayList<Sns>> response) {

                if(response.code() == 200){
                    snslist = response.body();

                    //몇번째 게시물을 가져오는가
                    //전체다 조회하는 코드는 파워리프팅관련 코드 참조
                    Sns sns = snslist.get(1);

                    Log.d("apiTest",snslist.toString());
                    Log.d("apiTest",sns.getTitle());
                    Log.d("apiTest",sns.getImg());
                    Log.d("apiTest",sns.getDate());
                    Log.d("apiTest",sns.getContent());
//                    adapter.replaceItem(list);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sns>> call, Throwable t) {

            }
        });

    }
}