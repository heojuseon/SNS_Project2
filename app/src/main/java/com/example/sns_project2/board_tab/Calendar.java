package com.example.sns_project2.board_tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sns_project2.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Calendar extends Fragment {

    public String readDay = null;
    public String str = null;

    CalendarView calendarview;
    Button cal_save_btn, cal_update_btn, cal_delete_btn;
    TextView cal_main, cal_date, cal_output;
    EditText edit_input;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarview = rootView.findViewById(R.id.calendarView);
        cal_save_btn = rootView.findViewById(R.id.calendar_save);
        cal_update_btn = rootView.findViewById(R.id.calendar_update);
        cal_delete_btn = rootView.findViewById(R.id.calendar_delete);
        cal_main = rootView.findViewById(R.id.calender_main);
        cal_date = rootView.findViewById(R.id.calendar_date);
        cal_output = rootView.findViewById(R.id.calendar_output);
        edit_input = rootView.findViewById(R.id.calendar_input);

        // CalendarView의 날짜를 선택했을 때 리스너를 구현
        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                cal_date.setVisibility(View.VISIBLE);
                cal_output.setVisibility(View.INVISIBLE);

                edit_input.setVisibility(View.VISIBLE);

                cal_save_btn.setVisibility(View.VISIBLE);
                cal_update_btn.setVisibility(View.INVISIBLE);
                cal_delete_btn.setVisibility(View.INVISIBLE);
                cal_date.setText(String.format("%d / %d / %d", year, month + 1, dayOfMonth));
                edit_input.setText("");

                checkDay(year, month, dayOfMonth);
            }
        });

        cal_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(readDay);
                str = edit_input.getText().toString();
                cal_output.setText(str);
                cal_save_btn.setVisibility(View.INVISIBLE);
                cal_update_btn.setVisibility(View.VISIBLE);
                cal_delete_btn.setVisibility(View.VISIBLE);
                edit_input.setVisibility(View.INVISIBLE);
                cal_output.setVisibility(View.VISIBLE);
            }
        });


        return rootView;
    }

    //안드로이드 기기에서 자료를 저장하는 방법은 크게 4가지가 있습니다.
    //내부 저장장치, 외부 저장장치, db에 저장, sharedpreferences가 있는데 openFileInput, openFileOutput은 내부 저장장치를 사용하여 저장합니다.
    //자바의 파일 입출력 스트림을 사용하며, openFileInput, openFileOutput을 사용하여 안드로이드 내부 저장장치에 파일을 생성하여 쓰고 읽기를 하는 방식입니다.

    //openFileInput : 하위 디렉터리에 있는 응용프로그램 파일을 읽기 모드로 오픈
    //openFileOutput : 하위 디렉터리에 있는 응용프로그램 파일을 쓰기 모드로 열거나 생성

    //Textview인 cal_output에 날짜를 확인하거나 저장된 일정을 확인하는 메소드
    private void checkDay(int year, int month, int dayOfMonth) {
        readDay = "" + year + "-" + (month + 1) + "" + "-" + dayOfMonth + ".txt";

        //openFileInput : 하위 디렉터리에 있는 응용프로그램 파일을 읽기 모드로 오픈
        FileInputStream fis;
        try {
//            fis = openFileInput(readDay);
            fis = getActivity().openFileInput(readDay);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str = new String(fileData);

            edit_input.setVisibility(View.INVISIBLE);
            cal_output.setVisibility(View.VISIBLE);
            cal_output.setText(str);

            cal_save_btn.setVisibility(View.INVISIBLE);
            cal_update_btn.setVisibility(View.VISIBLE);
            cal_delete_btn.setVisibility(View.VISIBLE);

            cal_update_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edit_input.setVisibility(View.VISIBLE);
                    cal_output.setVisibility(View.INVISIBLE);
                    edit_input.setText(str);

                    cal_save_btn.setVisibility(View.VISIBLE);
                    cal_update_btn.setVisibility(View.INVISIBLE);
                    cal_delete_btn.setVisibility(View.INVISIBLE);
                    cal_output.setText(edit_input.getText());
                }

            });
            cal_delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cal_output.setVisibility(View.INVISIBLE);
                    edit_input.setText("");
                    edit_input.setVisibility(View.VISIBLE);
                    cal_save_btn.setVisibility(View.VISIBLE);
                    cal_update_btn.setVisibility(View.INVISIBLE);
                    cal_delete_btn.setVisibility(View.INVISIBLE);
                    removeDiary(readDay);
                }
            });
            if (cal_output.getText() == null) {
                cal_output.setVisibility(View.INVISIBLE);
                cal_date.setVisibility(View.VISIBLE);
                cal_save_btn.setVisibility(View.VISIBLE);
                cal_update_btn.setVisibility(View.INVISIBLE);
                cal_delete_btn.setVisibility(View.INVISIBLE);
                edit_input.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //@SuppressLint는 Lint 검사를 사용 중지하기 위한 주석 코드이다.

    //Lint 검사 : 안드로이드 프로젝트 소스 파일을 검사하여 잠재적 버그를 찾아내고 경고해주는 도구

    //SuppressLint("중지할 내용") : 소괄호 안 큰 따옴표에 Lint 검사를 중지할 내용을 넣으면 검사중지 된다.

    //-> @SuppressLint("all");   //  파일에서 모든 Lint 문제 검사를 금지하려면 다음과 같이 all 키워드를 사용한다.

    //@SuppressLint("WrongConstant")
    //둘다 사용가능
    //Context.MODE_NO_LOCALIZED_COLLATORS 오류 해결하기 위해 사용
    @SuppressLint("all")
    private void saveDiary(String readDay) {
        FileOutputStream fos;
        try
        {
            fos = getActivity().openFileOutput(readDay, Context.MODE_NO_LOCALIZED_COLLATORS);
            String content = edit_input.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    @SuppressLint("WrongConstant")
    private void removeDiary(String readDay) {
        FileOutputStream fos;
        try
        {
            fos = getActivity().openFileOutput(readDay, Context.MODE_NO_LOCALIZED_COLLATORS);
            String content = "";
            fos.write((content).getBytes());
            fos.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}