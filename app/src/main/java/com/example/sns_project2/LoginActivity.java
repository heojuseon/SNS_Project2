package com.example.sns_project2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sns_project2.member_data.Login;
import com.example.sns_project2.member_data.ResDTO;
import com.example.sns_project2.member_data.User;
import com.example.sns_project2.network.SignUpAPI;
import com.example.sns_project2.network.SignUpRequest;
import com.example.sns_project2.member_data.SignUp;
import com.example.sns_project2.tab.Tab1_Map;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

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

    Button loginbtn, signupbtn, button;

    ImageButton kakaobtn, naverbtn, facebtn;


    final String TAG = "### google Login ###";
    //구글
    GoogleSignInClient mGoogleSignInClient;
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 구글로그인
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {

                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                            signInResult(task);

                        } else {

                        }
                    }
                });

        //앱에 필요한 사용자 데이터를 요청하도록 로그인 옵션을 설정한다.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestProfile()
                .build();
        //GoogleSignInOptions을 사용해 GoogleSignInClient 객체를 생성
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        facebtn = findViewById(R.id.imageButton7);
        facebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("facebook", " click");

                Intent intent = mGoogleSignInClient.getSignInIntent();
                resultLauncher.launch(intent);

            }
        });




        edituserid = findViewById(R.id.userID);
        edituserpw = findViewById(R.id.userPW);

        list = new ArrayList<>();
        retrofit = SignUpRequest.getClient();
        signupAPI = retrofit.create(SignUpAPI.class);

        loginbtn = findViewById(R.id.lgnbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addLogin(edituserid.getText().toString(), edituserpw.getText().toString());

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


    }

    //구글
    private void signInResult(Task<GoogleSignInAccount> completedTask) {



        try {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);

            if (acct != null) {
                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Log.d(TAG, ":personName "+personName);
                Log.d(TAG, ":personGivenName "+personGivenName);
                Log.d(TAG, ":personEmail "+personEmail);
                Log.d(TAG, ":personId "+personId);
                Log.d(TAG, ":personFamilyName "+personFamilyName);
                Log.d(TAG, ":personPhoto "+personPhoto);

                addLogin(personEmail,"");

            }
        } catch (ApiException e) {

            Log.e(TAG, "signInResult:failed code=" + e.getStatusCode());

        }
    }

    void addLogin(String userid, String userpw) {
        Log.d("apiTest ","addLogin");



        signupAPI.addLogin(userid, userpw).enqueue(new Callback<ResDTO>() {
            @Override
            public void onResponse(Call<ResDTO> call, Response<ResDTO> response) {

                if(response.code() == 200){
                    Log.d("apiTest",response.toString());
                    Intent intent = new Intent(getApplicationContext(), MainActivity_Fragment.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<ResDTO> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });

    }
}