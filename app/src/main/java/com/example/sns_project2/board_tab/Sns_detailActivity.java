package com.example.sns_project2.board_tab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sns_project2.R;
import com.example.sns_project2.Write_Board;
import com.example.sns_project2.sns_data.Sns;
import com.example.sns_project2.sns_network.SnsAPI;
import com.example.sns_project2.sns_network.SnsRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Sns_detailActivity extends AppCompatActivity {
    SnsAPI snsAPI;
    ArrayList<Sns> snslist;
    Retrofit retrofit;

    Sns_Adapter sns_adapter;
    RecyclerView recyclerView;

    TextView textView;

    Button update_btn, delete_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snsdetail);

        textView = findViewById(R.id.detail);
        update_btn = findViewById(R.id.update);
        delete_btn = findViewById(R.id.delete);


        snslist = new ArrayList<>();
        retrofit = SnsRequest.getClient();
        snsAPI = retrofit.create(SnsAPI.class);


        Intent i = getIntent();
        Sns item = (Sns) i.getSerializableExtra("snsData");
        textView.setText(item.getTitle() + "\n" + item.getImg() + "\n" + item.getDate() + "\n" + item.getContent());

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Write_Board.class);
                startActivity(intent);
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSns();
            }
        });



    }

    private void deleteSns() {
        Log.d("apiTest ","get_deleteSnsList");

        snsAPI.deleteSns(10).enqueue(new Callback<Sns>() {

            @Override
            public void onResponse(Call<Sns> call, Response<Sns> response) {

                if(response.code() == 200){

                    Log.d("apiTest",response.toString());

//                    sns_adapter = new Sns_Adapter();
//                    recyclerView.setAdapter(sns_adapter);
//                    sns_adapter.setItems(snslist);



                }
            }

            @Override
            public void onFailure(Call<Sns> call, Throwable t) {

            }
        });
    }
}