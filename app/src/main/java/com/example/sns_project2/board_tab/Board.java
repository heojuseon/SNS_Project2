package com.example.sns_project2.board_tab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sns_project2.R;
import com.example.sns_project2.Write_Board;
import com.example.sns_project2.sns_data.Sns;
import com.example.sns_project2.sns_data.Sns_board;
import com.example.sns_project2.sns_network.SnsAPI;
import com.example.sns_project2.sns_network.SnsRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Board extends Fragment {
    ArrayList<Sns_board> snslist;
    Retrofit retrofit;
    SnsAPI snsAPI;

    Button wrtbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);

        snslist = new ArrayList<>();
        retrofit = SnsRequest.getClient();
        snsAPI = retrofit.create(SnsAPI.class);

        getSnsList();


        wrtbtn = rootView.findViewById(R.id.writebtn);
        wrtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Write_Board.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void getSnsList() {

        Log.d("apiTest ","getSnsList");
        snsAPI = retrofit.create(SnsAPI.class);

        snsAPI.getSnsList().enqueue(new Callback<ArrayList<Sns_board>>() {
            @Override
            public void onResponse(Call<ArrayList<Sns_board>> call, Response<ArrayList<Sns_board>> response) {

                if(response.code() == 200){

                    snslist = response.body();

                    //몇번째 게시물을 가져오는가
                    //전체다 조회하는 코드는 파워리프팅관련 코드 참조
                    for (int i = 0; i < snslist.size(); i++){
                        Sns_board sns_board = snslist.get(i);

                        Log.d("apiTest",snslist.toString());
                        Log.d("apiTest",sns_board.getTitle());
                        Log.d("apiTest",sns_board.getImg());
                        Log.d("apiTest",sns_board.getDate());
                        Log.d("apiTest",sns_board.getContent());

                    }
//                    Sns sns = snslist.get(1);
//
//                    Log.d("apiTest",snslist.toString());
//                    Log.d("apiTest",sns.getTitle());
//                    Log.d("apiTest",sns.getImg());
//                    Log.d("apiTest",sns.getDate());
//                    Log.d("apiTest",sns.getContent());
////                    adapter.replaceItem(list);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sns_board>> call, Throwable t) {

            }
        });

    }
}