package com.example.sns_project2.training_tab;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.sns_project2.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class Running extends Fragment implements YouTubeThumbnailView.OnInitializedListener {

    YouTubeThumbnailView youTubeThumbnailView;
    YouTubeThumbnailLoader youTubeThumbnailLoader;

    ImageView imageviewThumbnail, imageviewThumbnail2;

    public String ID ="lKwZ2DU4P-A";


    public static final String API_KEY = "AIzaSyCzmawqBqx01jL2nzmdCH4urzpW5ygclmM";


    //https://www.youtube.com/watch?v=lKwZ2DU4P-A

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_running, container, false);

        //1번 썸네일
        youTubeThumbnailView = rootView.findViewById(R.id.thumbnail);
        youTubeThumbnailView.initialize(API_KEY, Running.this);
        setListener();
        

        //2번 썸네일
        imageviewThumbnail = rootView.findViewById(R.id.thumbnail_img);
        Glide.with(getContext()).load("https://img.youtube.com/vi/VNQpP6C1fJg/0.jpg")
                .thumbnail(0.1f)
                .into(imageviewThumbnail);
        imageviewThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=VNQpP6C1fJg"));
                startActivity(intent);
            }
        });

        //3번 썸네일
        imageviewThumbnail2 = rootView.findViewById(R.id.thumbnail_img2);
        Glide.with(getContext()).load("https://img.youtube.com/vi/swRNeYw1JkY/0.jpg")
                .thumbnail(0.1f)
                .into(imageviewThumbnail2);
        imageviewThumbnail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=swRNeYw1JkY"));
                startActivity(intent);
            }
        });

        return rootView;
    }



    private void setListener() {
        youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent = YouTubeStandalonePlayer.createPlaylistIntent(getActivity(), API_KEY, ID, 0, 0, true, false);
//                intent.resolveActivity(getContext().getPackageManager());
//                startActivity(intent);
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.youtube.com/watch?v=lKwZ2DU4P-A"))
                        .setPackage("com.google.android.youtube"));
            }
        });
    }


    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader thumbnailLoader) {

        //1번
        youTubeThumbnailLoader = thumbnailLoader;
        youTubeThumbnailLoader.setVideo(ID);

        thumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                Log.d(TAG, "onThumbnailLoaded");
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                Log.d(TAG, "onThumbnailfail");
            }
        });
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
        Log.d("에러", "에러");
    }

}