package com.example.sns_project2.tab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sns_project2.R;
import com.example.sns_project2.board_tab.Board;
import com.example.sns_project2.board_tab.Calendar;
import com.example.sns_project2.sns_data.Sns;
import com.example.sns_project2.sns_network.SnsAPI;
import com.example.sns_project2.training_tab.Health;
import com.example.sns_project2.training_tab.Running;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class Tab3_Sns extends Fragment {
    Board board;
    Calendar calendar;

    TabLayout board_tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tab3__sns, container, false);

        board = new Board();
        calendar = new Calendar();

//        getChildFragmentManager().beginTransaction().replace(R.id.training_fram, health).commit();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.board_fram, board).commit();
        board_tabLayout = rootView.findViewById(R.id.tablayout2);
        board_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.board_fram, board).commit();

                        break;

                    case 1:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.board_fram, calendar).commit();

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return rootView;
    }
}