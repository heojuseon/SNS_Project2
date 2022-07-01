package com.example.sns_project2.example;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sns_project2.R;
import com.example.sns_project2.board_tab.Sns_Adapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements YoutubeItemClickListener{

    Context context;
    ArrayList<Model> list;

    YoutubeItemClickListener listener;

    public Adapter(Context context, ArrayList<Model> model) {
        this.context = context;
        this.list = model;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Model model = list.get(position);
        holder.textView.setText(model.getTitle());
        Picasso.get().load(model.getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void youtubeOnItemClickListener(YoutubeItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onyoutubeclick(MyViewHolder holder, View view, int position) {
        if (listener != null){
            listener.onyoutubeclick(holder, view, position);
        }
    }

    public Model getItem(int position) {
      return list.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.youtube_thumbnail);
            textView = itemView.findViewById(R.id.youtube_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null){
                        listener.onyoutubeclick(Adapter.MyViewHolder.this, view, position);
                    }
                }
            });
        }
    }

}
