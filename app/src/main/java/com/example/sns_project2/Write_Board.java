package com.example.sns_project2;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sns_project2.sns_data.Sns;
import com.example.sns_project2.sns_data.Sns_board;
import com.example.sns_project2.sns_network.SnsAPI;
import com.example.sns_project2.sns_network.SnsRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Write_Board extends AppCompatActivity {
    ArrayList<Sns_board> list;
    Retrofit retrofit;
    SnsAPI snsAPI;

    EditText editdate;
    EditText edittitle;
    EditText editimg;
    EditText editcontent;

    Button insertbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_board);
        //회원가입할때 사용한 코드 참조

        editdate = findViewById(R.id.editDate);
        edittitle = findViewById(R.id.editTitle);
        editimg = findViewById(R.id.editImg);
        editcontent = findViewById(R.id.editContent);

        list = new ArrayList<>();
        retrofit = SnsRequest.getClient();
        snsAPI = retrofit.create(SnsAPI.class);

        insertbtn = findViewById(R.id.insert);
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSns();
            }

        });



    }




    private void addSns() {
        Log.d("apiTest ","addSns");
        Sns_board sns_board = new Sns_board();

        sns_board.setDate(editdate.getText().toString());
        sns_board.setTitle(edittitle.getText().toString());
        sns_board.setImg(editimg.getText().toString());
        sns_board.setContent(editcontent.getText().toString());

        snsAPI.addSns(sns_board).enqueue(new Callback<Sns_board>() {
            @Override
            public void onResponse(Call<Sns_board> call, Response<Sns_board> response) {

                if(response.code() == 200){

                    Log.d("apiTest",response.toString());
                }
            }

            @Override
            public void onFailure(Call<Sns_board> call, Throwable t) {

            }
        });

    }



}