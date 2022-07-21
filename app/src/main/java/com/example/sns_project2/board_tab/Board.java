package com.example.sns_project2.board_tab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sns_project2.R;
import com.example.sns_project2.Write_Board;
import com.example.sns_project2.sns_data.Sns;
import com.example.sns_project2.sns_data.Sns_board;
import com.example.sns_project2.sns_network.SnsAPI;
import com.example.sns_project2.sns_network.SnsRequest;
import com.example.sns_project2.tab.Tab1_Map;


import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Board extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    ArrayList<Sns> snslist;
    Retrofit retrofit;
    SnsAPI snsAPI;

    Button wrtbtn;

    Sns_Adapter sns_adapter;
    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);

        snslist = new ArrayList<>();
        retrofit = SnsRequest.getClient();
        snsAPI = retrofit.create(SnsAPI.class);


        swipeRefreshLayout = rootView.findViewById(R.id.swiper_board);
        swipeRefreshLayout.setOnRefreshListener(this);

        getSnsList();

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                //새로 고침 코드
////                updateLayoutView();
//
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });




        recyclerView = rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

//        //getSnsList()메소드 안에 onResponse()에서 구현해야 한다
//        sns_adapter = new Sns_Adapter();
//        recyclerView.setAdapter(sns_adapter);


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

//    // 당겨서 새로고침 했을 때 뷰 변경 메서드
//    private void updateLayoutView() {
//    }

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
                    for (int i = 0; i < snslist.size(); i++){
                        Sns sns = snslist.get(i);

                        Log.d("apiTest",snslist.toString());
                        Log.d("apiTest", String.valueOf(sns.getSid()));
                        Log.d("apiTest",sns.getTitle());
                        Log.d("apiTest",sns.getImg());
                        Log.d("apiTest",sns.getDate());
                        Log.d("apiTest",sns.getContent());

                    }

                    //sns_adapter = new Sns_Adapter(); 생성 안하면 NullPointerException 오류 발생
                    sns_adapter = new Sns_Adapter();
                    recyclerView.setAdapter(sns_adapter);
                    sns_adapter.setItems(snslist);



                    //getSnsList()메소드에서 리스트를 출력하고 있기 때문에 OnSnsItemClickListener를 여기서 출력한다
                    sns_adapter.setOnItemClickListener(new OnSnsItemClickListener() {
                        @Override
                        public void onItemClick(Sns_Adapter.ViewHolder holder, View view, int position) {
                            Sns item = sns_adapter.getItem(position);
                            Toast.makeText(getContext(), "게시물 선택됨: " + item.getTitle(),
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getActivity(), Sns_detailActivity.class);
                            intent.putExtra("snsData", (Serializable) item);
                            startActivity(intent);
                        }
                    });

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
            public void onFailure(Call<ArrayList<Sns>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onRefresh() {
        getSnsList();
        swipeRefreshLayout.setRefreshing(false);
    }
}