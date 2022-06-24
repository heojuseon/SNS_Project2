package com.example.sns_project2.tab;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.sns_project2.R;
import com.example.sns_project2.training_tab.Health;
import com.example.sns_project2.training_tab.Health2;
import com.example.sns_project2.training_tab.Running;
import com.google.android.material.tabs.TabLayout;

public class Tab2_Training extends Fragment {

    Health health;
    Health2 health2;
    Running running;

    TabLayout mtablayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tab2__training, container, false);

        health = new Health();
        health2 = new Health2();
        running = new Running();

//        getChildFragmentManager().beginTransaction().replace(R.id.training_fram, health).commit();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.training_fram, health).commit();
        mtablayout = rootView.findViewById(R.id.tablayout);
        mtablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
//                        health = new Health();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.training_fram, health).commit();

                        break;

                    case 1:
//                        running = new Running();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.training_fram, running).commit();

                        break;

                    case 2:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.training_fram, health2).commit();
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





//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//
//        inflater.inflate(R.menu.training_tab,menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
//
//    }
}