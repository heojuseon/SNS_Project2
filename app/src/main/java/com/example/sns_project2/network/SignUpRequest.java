package com.example.sns_project2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpRequest {

    private final static String BASE_URL = "http://10.0.2.2:8080/api/"; // 서버 URL
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }





//    private Map<String, String> map;
//
//    public SignUpRequest(String userID, String userPW, String userName, Response.Listener<String> listener) {
//        super(Method.POST, JDBC_URL, listener, null);
//
//
//        map = new HashMap<>();
//        map.put("userID", userID);
//        map.put("userPW", userPW);
//        map.put("userName", userName);
//    }
//
//    @Nullable
//    @Override
//    protected Map<String, String> getParams() throws AuthFailureError {
//        return map;
//    }
}
