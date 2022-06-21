package com.example.sns_project2.board_tab;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnSnsItemClickListener {
    public void onItemClick(Sns_Adapter.ViewHolder holder, View view, int position);
}
