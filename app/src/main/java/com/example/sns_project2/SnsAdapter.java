package com.example.sns_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sns_project2.member_data.ResDTO;
import com.example.sns_project2.member_data.SignUp;
import com.example.sns_project2.network.SignUpAPI;
import com.example.sns_project2.network.SignUpRequest;
import com.example.sns_project2.sns_data.Sns;
import com.example.sns_project2.sns_data.Sns_board;
import com.example.sns_project2.sns_network.SnsAPI;
import com.example.sns_project2.sns_network.SnsRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SnsAdapter extends AppCompatActivity {
    ArrayList<Sns> snslist;
    Retrofit retrofit;
    SnsAPI snsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sns);

//        snslist = new ArrayList<>();
//        retrofit = SnsRequest.getClient();
//        snsAPI = retrofit.create(SnsAPI.class);

//         getSnsList();
    }

//    private void getSnsList() {
//        Log.d("apiTest ","getSnsList");
//        snsAPI = retrofit.create(SnsAPI.class);
//
//        snsAPI.getSnsList().enqueue(new Callback<ArrayList<Sns>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Sns>> call, Response<ArrayList<Sns>> response) {
//
//                if(response.code() == 200){
//                    snslist = response.body();
//                    Sns sns = snslist.get(1);
//
//                    Log.d("apiTest",snslist.toString());
//                    Log.d("apiTest",sns.getTitle());
//                    Log.d("apiTest",sns.getImg());
//                    Log.d("apiTest",sns.getDate());
//                    Log.d("apiTest",sns.getContent());
////                    adapter.replaceItem(list);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Sns>> call, Throwable t) {
//
//            }
//        });
//    }
}