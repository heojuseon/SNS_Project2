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
    YouTubePlayerView youTubePlayerView;
    LinearLayout recipeViewLinearLayout;

//    //유튜브 API KEY와 동영상 ID 변수 설정
//    private static String API_KEY = "AIzaSyCzmawqBqx01jL2nzmdCH4urzpW5ygclmM";
//
//    //https://www.youtube.com/watch?v=bn8MSLQ1Gsg&list=PLdER_vr5Q2R7OaI58ksEBkn9k56pHo-AT&index=48 ▶ 유튜브 동영상 v= 다음 부분이 videoId
//    private static String videoId = "bn8MSLQ1Gsg&list=PLdER_vr5Q2R7OaI58ksEBkn9k56pHo-AT&index=48";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_health, container, false);

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
                String videoId = "wmRVz6GfHDs";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });


        return rootView;
    }


}