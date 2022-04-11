package com.example.sns_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sns_project2.member_data.Login;
import com.example.sns_project2.member_data.User;
import com.example.sns_project2.network.SignUpAPI;
import com.example.sns_project2.network.SignUpRequest;
import com.example.sns_project2.member_data.SignUp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    ArrayList<SignUp> list;
    Retrofit retrofit;
    SignUpAPI signupAPI;

    SignUpRequest signUpRequest;

    EditText edituserid, edituserpw;

    Button loginbtn;
    Button signupbtn;
    Button button;

    ImageButton kakaobtn;
    ImageButton naverbtn;
    ImageButton facebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edituserid = findViewById(R.id.userID);
        edituserpw = findViewById(R.id.userPW);

        list = new ArrayList<>();
        retrofit = SignUpRequest.getClient();
        signupAPI = retrofit.create(SignUpAPI.class);

        loginbtn = findViewById(R.id.lgnbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addLogin();

//                Intent intent = new Intent(getApplicationContext(), MainActivity_Fragment.class);
//                startActivity(intent);
            }
        });

        signupbtn = findViewById(R.id.signup);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });


        button = findViewById(R.id.button3);
        button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); //버튼(텍스트) 아래 밑줄 설정
        button.setText(getString(R.string.underlined_dynamic_text, "아이디 / 비밀번호 찾기"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("클릭함", "클릭");
            }
        });


        kakaobtn = findViewById(R.id.imageButton5);
        kakaobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("kakao", "click");
            }
        });
        naverbtn = findViewById(R.id.imageButton6);
        naverbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("naver", "click");
            }
        });
        facebtn = findViewById(R.id.imageButton7);
        facebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("facebook"," click");
            }
        });

    }

    void addLogin() {
        Log.d("apiTest ","addLogin");
        Login login = new Login();

//        login.setUserId(edituserid.getText().toString());
//        login.setUserPW(edituserpw.getText().toString());



        signupAPI.addLogin(edituserid.getText().toString(), edituserpw.getText().toString()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.code() == 200){
                    Log.d("apiTest",response.toString());
                    Intent intent = new Intent(getApplicationContext(), MainActivity_Fragment.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });


    }
}