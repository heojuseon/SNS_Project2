package com.example.sns_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity {
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

        loginbtn = findViewById(R.id.lgnbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_Fragment.class);
                startActivity(intent);
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
}