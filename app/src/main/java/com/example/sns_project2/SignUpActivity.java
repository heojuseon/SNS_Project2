package com.example.sns_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {
    Button cancelbtn;
    Button signokbtn;

    EditText editid;
    EditText editpw;
    EditText editname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        cancelbtn = findViewById(R.id.signcancel);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        editid = findViewById(R.id.editID);
        editpw = findViewById(R.id.editPW);
        editname = findViewById(R.id.editName);

        signokbtn = findViewById(R.id.signok);
        signokbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = editid.getText().toString();
                String userPW = editpw.getText().toString();
                String userName = editname.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){//등록 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);

                            }else {//실패할 경우
                                Toast.makeText(getApplicationContext(), "가입 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };


                //서버로 volley이용해 요청
                SignUpRequest signUpRequest = new SignUpRequest(userID, userPW, userName, responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
                requestQueue.add(signUpRequest);

            }
        });
    }



}