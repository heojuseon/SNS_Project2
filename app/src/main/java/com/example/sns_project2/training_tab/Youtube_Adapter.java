//package com.example.sns_project2.training_tab;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Handler;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.sns_project2.R;
//import com.example.sns_project2.youtubeAPI.ItemsBean;
//import com.example.sns_project2.youtubeAPI.Youtube;
//
//import java.io.InputStream;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class Youtube_Adapter extends RecyclerView.Adapter<Youtube_Adapter.ViewHolder>{
//    ArrayList<Youtube> y_data = new ArrayList<>();
//
//    Context context;
//
//    public Youtube_Adapter(Context context, ArrayList<Youtube> y_datas) {
//        this.context = context;
//        this.y_data = y_datas;
//    }
//
//
//    @NonNull
//    @Override
//    public Youtube_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View itemView = inflater.inflate(R.layout.youtube_item, parent, false);
//
//        return new Youtube_Adapter.ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Youtube_Adapter.ViewHolder holder, int position) {
//        Youtube youtube = y_data.get(position);
//        holder.bind(youtube);
//    }
//
//    @Override
//    public int getItemCount() {
//        return y_data.size();
//    }
//
//    public void addItem(Youtube youtube) {
//        y_data.add(youtube);
//    }
//
//    public void setItems(ArrayList<Youtube> y_datas) {
//        this.y_data = y_datas;
//    }
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//        Bitmap bitmap;
//        Handler handler = new Handler();
//
//        ImageView imageView;
//        TextView textView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.youtube_thumbnail);
//            textView = itemView.findViewById(R.id.youtube_title);
//        }
//
//        public void bind(Youtube youtube) {
//
//            //이미지 url을 가져오기 위해 Thread사용
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        if(youtube.getYoutube_img() != null){
//                            URL url = new URL(youtube.getYoutube_img());
//                            InputStream instream = url.openStream();
//                            bitmap = BitmapFactory.decodeStream(instream);
//                            handler.post(new Runnable() {//외부쓰레드에서 메인 UI에 접근하기 위해 Handler를 사용
//                                @Override
//                                public void run() { // 화면에 그려줄 작업
//                                    imageView.setImageBitmap(bitmap);
//
//
//                                    //이미지 사이즈 조정
//                                    Glide.with(imageView)
//
//                                            .load(youtube.getYoutube_img())
//
//                                            .override(500, 500)
//
//                                            .into(imageView);
//
//                                }
//                            });
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            t.start();
//
//            textView.setText(youtube.getYoutube_title());
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(imageView.getContext(), "클릭하였음", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//}
