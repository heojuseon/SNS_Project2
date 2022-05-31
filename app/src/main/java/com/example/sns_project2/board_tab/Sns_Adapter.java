package com.example.sns_project2.board_tab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sns_project2.R;
import com.example.sns_project2.sns_data.Sns;

import java.io.InputStream;
import java.net.URL;
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
        Bitmap bitmap;
        Handler handler = new Handler();

        TextView textView;
        TextView textView2;
//        TextView textView3;
        ImageView imageView;
        TextView textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.board_date);
            textView2 = itemView.findViewById(R.id.board_title);
//            textView3 = itemView.findViewById(R.id.board_img);
            imageView = itemView.findViewById(R.id.board_img);
            textView4 = itemView.findViewById(R.id.board_content);
        }
        public void setItem(Sns item) {

            //이미지 url을 가져오기 위해 Thread사용
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(item.getImg() != null){
                            URL url = new URL(item.getImg());
                            InputStream instream = url.openStream();
                            bitmap = BitmapFactory.decodeStream(instream);
                            handler.post(new Runnable() {//외부쓰레드에서 메인 UI에 접근하기 위해 Handler를 사용
                                @Override
                                public void run() { // 화면에 그려줄 작업
                                    imageView.setImageBitmap(bitmap);


                                    //이미지 사이즈 조정
                                    Glide.with(imageView)

                                            .load(item.getImg())

                                            .override(500, 500)

                                            .into(imageView);

                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();


            textView.setText(item.getDate());
            textView2.setText(item.getTitle());
//            textView3.setText(item.getImg());
            textView4.setText(item.getContent());
        }
    }
}
