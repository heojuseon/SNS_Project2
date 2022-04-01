package com.example.sns_project2;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class SignUpRequest extends StringRequest {

    //서버 URL설정(H2 DB)
    final static private String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";



    private Map<String, String> map;

    public SignUpRequest(String userID, String userPW, String userName, Response.Listener<String> listener) {
        super(Method.POST, JDBC_URL, listener, null);


        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPW", userPW);
        map.put("userName", userName);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
