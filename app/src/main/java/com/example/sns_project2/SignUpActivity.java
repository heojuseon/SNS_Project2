package com.example.sns_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.sns_project2.network.SignUpAPI;
import com.example.sns_project2.network.SignUpRequest;
import com.example.sns_project2.siginup_data.SignUp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    ArrayList<SignUp> list;
    Retrofit retrofit;
    SignUpAPI signupAPI;

    Button cancelbtn;
    Button signokbtn;

    EditText editid;
    EditText editpw;
    EditText editname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editid = findViewById(R.id.editID);
        editpw = findViewById(R.id.editPW);
        editname = findViewById(R.id.editName);

        list = new ArrayList<>();
        retrofit = SignUpRequest.getClient();
        signupAPI = retrofit.create(SignUpAPI.class);


        cancelbtn = findViewById(R.id.signcancel);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signokbtn = findViewById(R.id.signok);
        signokbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSignUp();
            }
        });




    }

    void addSignUp() {
        Log.d("apiTest ","addSignUp");
        SignUp signUp = new SignUp();

        String userid = editid.getText().toString();
        String userpw = editpw.getText().toString();
        String username = editname.getText().toString();



        signUp.setUserId(userid);
        signUp.setUserPW(userpw);
        signUp.setUserName(username);

        signupAPI.addSignUp(signUp).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.code() == 200){
                    Log.d("apiTest",response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }


}