package com.example.sns_project2.training_tab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sns_project2.R;

import com.example.sns_project2.board_tab.Sns_Adapter;
import com.example.sns_project2.example.Adapter;
import com.example.sns_project2.example.Model;
import com.example.sns_project2.example.YoutubeItemClickListener;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class Health2 extends Fragment {

    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Model> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_health2, container, false);

        recyclerView = rootView.findViewById(R.id.youtube_recycler);
        adapter = new Adapter(Health2.this.getContext(), list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        youtube_data();

        adapter.youtubeOnItemClickListener(new YoutubeItemClickListener() {
            @Override
            public void onyoutubeclick(Adapter.MyViewHolder holder, View view, int position) {
                Model item = adapter.getItem(position);
                Toast.makeText(getContext(), "선택: " + item.getTitle(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + item.getVideoId()));
                startActivity(intent);
            }
        });


        return rootView;
    }

    private void youtube_data(){
//        String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&key=AIzaSyCzmawqBqx01jL2nzmdCH4urzpW5ygclmM&q=박승현&maxResults=20&order=viewCount&=";

        String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLdER_vr5Q2R7OaI58ksEBkn9k56pHo-AT&key=AIzaSyCzmawqBqx01jL2nzmdCH4urzpW5ygclmM&maxResults=50";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("items");

                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

//                                JSONObject jsonvideoid = jsonObject1.getJSONObject("id");
                                JSONObject jsonsnippet = jsonObject1.getJSONObject("snippet");
                                JSONObject jsonthumbnail = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");
                                JSONObject jsonvideoid = jsonsnippet.getJSONObject("resourceId");


                                Model md = new Model();
                                if (i != 1 && i != 2){
//                                    md.setVideoId(jsonvideoid.getString("videoId"));
                                    md.setTitle(jsonsnippet.getString("title"));
                                    md.setUrl(jsonthumbnail.getString("url"));
                                    md.setVideoId(jsonvideoid.getString("videoId"));
                                    list.add(md);
                                }

                            }
                            if (list.size() > 0){
                                adapter.notifyDataSetChanged();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Health2.this.getContext(), "error", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}