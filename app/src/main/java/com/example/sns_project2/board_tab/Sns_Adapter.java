package com.example.sns_project2.board_tab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sns_project2.R;
import com.example.sns_project2.sns_data.Sns;

import java.util.ArrayList;

public class Sns_Adapter extends RecyclerView.Adapter<Sns_Adapter.ViewHolder> {
    ArrayList<Sns> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.board_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sns snsitem = items.get(position);
        holder.setItem(snsitem);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //연결한 어뎁터를 Sns클래스에 담겨있는 정보들을 불러와 화면 연결 위해 setItems라는 메소드 생성
    public void setItems(ArrayList<Sns> items){
        this.items = items;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.board_date);
            textView2 = itemView.findViewById(R.id.board_title);
            textView3 = itemView.findViewById(R.id.board_img);
            textView4 = itemView.findViewById(R.id.board_content);
        }
        public void setItem(Sns item) {
            textView.setText(item.getDate());
            textView2.setText(item.getTitle());
            textView3.setText(item.getImg());
            textView4.setText(item.getContent());
        }
    }
}
