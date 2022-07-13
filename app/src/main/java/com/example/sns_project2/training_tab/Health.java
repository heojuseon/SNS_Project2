package com.example.sns_project2.training_tab;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.fragment.app.Fragment;


import com.example.sns_project2.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class Health extends Fragment {
    YouTubePlayerView youTubePlayerView, youTubePlayerView2, youTubePlayerView3, youTubePlayerView4, youTubePlayerView5, youTubePlayerView6;

    LinearLayout recipeViewLinearLayout, recipeViewLinearLayout2, recipeViewLinearLayout3, recipeViewLinearLayout4, recipeViewLinearLayout5,
            recipeViewLinearLayout6;


//    //유튜브 API KEY와 동영상 ID 변수 설정
//    private static String API_KEY = "AIzaSyCzmawqBqx01jL2nzmdCH4urzpW5ygclmM";
//
//    //https://www.youtube.com/watch?v=bn8MSLQ1Gsg&list=PLdER_vr5Q2R7OaI58ksEBkn9k56pHo-AT&index=48 ▶ 유튜브 동영상 v= 다음 부분이 videoId
//    private static String videoId = "bn8MSLQ1Gsg&list=PLdER_vr5Q2R7OaI58ksEBkn9k56pHo-AT&index=48";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_health, container, false);

        //1번 영상
        recipeViewLinearLayout = rootView.findViewById(R.id.recipe_view_linearLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 30;

        youTubePlayerView = new YouTubePlayerView(Health.this.getContext());
        youTubePlayerView.setLayoutParams(params);
        recipeViewLinearLayout.addView(youTubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = "bn8MSLQ1Gsg";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });


        //2번 영상
        recipeViewLinearLayout2 = rootView.findViewById(R.id.recipe_view_linearLayout2);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.bottomMargin = 30;

        youTubePlayerView2 = new YouTubePlayerView(Health.this.getContext());
        youTubePlayerView2.setLayoutParams(params1);
        recipeViewLinearLayout2.addView(youTubePlayerView2);
        getLifecycle().addObserver(youTubePlayerView2);

        youTubePlayerView2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer2) {
                String videoID2 = "wmRVz6GfHDs";
                youTubePlayer2.loadVideo(videoID2, 0);
            }
        });

        //3번 영상
        recipeViewLinearLayout3 = rootView.findViewById(R.id.recipe_view_linearLayout3);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.bottomMargin = 30;

        youTubePlayerView3 = new YouTubePlayerView(Health.this.getContext());
        youTubePlayerView3.setLayoutParams(params2);
        recipeViewLinearLayout3.addView(youTubePlayerView3);
        getLifecycle().addObserver(youTubePlayerView3);

        youTubePlayerView3.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer3) {
                String videoID3 = "n3FWKzp8qFI";
                youTubePlayer3.loadVideo(videoID3, 0);
            }
        });

        //4번 영상
        recipeViewLinearLayout4 = rootView.findViewById(R.id.recipe_view_linearLayout4);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params3.bottomMargin = 30;

        youTubePlayerView4 = new YouTubePlayerView(Health.this.getContext());
        youTubePlayerView4.setLayoutParams(params3);
        recipeViewLinearLayout4.addView(youTubePlayerView4);
        getLifecycle().addObserver(youTubePlayerView4);

        youTubePlayerView4.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer4) {
                String videoID4 = "yIe5_WWgX-I";
                youTubePlayer4.loadVideo(videoID4, 0);
            }
        });

        //5번 영상
        recipeViewLinearLayout5 = rootView.findViewById(R.id.recipe_view_linearLayout5);
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params4.bottomMargin = 30;

        youTubePlayerView5 = new YouTubePlayerView(Health.this.getContext());
        youTubePlayerView5.setLayoutParams(params4);
        recipeViewLinearLayout5.addView(youTubePlayerView5);
        getLifecycle().addObserver(youTubePlayerView5);

        youTubePlayerView5.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer5) {
                String videoID5 = "lOgtAR6_TlE";
                youTubePlayer5.loadVideo(videoID5, 0);
            }
        });

        //6번 영상
        recipeViewLinearLayout6 = rootView.findViewById(R.id.recipe_view_linearLayout6);
        LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params5.bottomMargin = 30;

        youTubePlayerView6 = new YouTubePlayerView(Health.this.getContext());
        youTubePlayerView6.setLayoutParams(params5);
        recipeViewLinearLayout6.addView(youTubePlayerView6);
        getLifecycle().addObserver(youTubePlayerView6);

        youTubePlayerView6.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer6) {
                String videoID6 = "V0JbCxuh-_4";
                youTubePlayer6.loadVideo(videoID6, 0);
            }
        });

        return rootView;
    }


}