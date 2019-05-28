package com.tisory.webnautes.helloworld;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.ArrayList;

public class LocalSearch extends AppCompatActivity implements View.OnClickListener {
    private Spinner Do;
    private Spinner Si;
    private Spinner Attraction;

    int mYear, mMonth, mDay, mHour, mMinute;
    TextView textView1;
    TextView textView2;
    TextView time1;
    TextView time2;
    //TextView searchgender;
    //TextView searchid;

    ArrayList<String> Do_arrayList;
    ArrayAdapter<String> Do_arrayAdapter;

    ArrayList<String> Si_arrayList;
    ArrayAdapter<String> Si_arrayAdapter;

    ArrayList<String> Attraction_arrayList;
    ArrayAdapter<String> Attraction_arrayAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localsearch);

        Button select1 = (Button)findViewById(R.id.select1);
        select1.setOnClickListener(this);

        Button select2 = (Button)findViewById(R.id.select2);
        select2.setOnClickListener(this);

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);

        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);

        Button searchgo = (Button)findViewById(R.id.searchgo);
        searchgo.setOnClickListener(this);

        textView1 = (TextView)findViewById((R.id.textView1));
        textView2 = (TextView)findViewById((R.id.textView2));
        time1 = (TextView)findViewById(R.id.time1);
        time2 = (TextView)findViewById(R.id.time2);
        //searchgender = (TextView)findViewById(R.id.searchgender);
        //searchid = (TextView)findViewById(R.id.searchid);

        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);

        UpdateNow();

        Do_arrayList = new ArrayList<>();
        Do_arrayList.add("충남");
        Do_arrayList.add("서울");
        Do_arrayList.add("대전");
        Do_arrayList.add("대구");
        Do_arrayList.add("부산");
        Do_arrayList.add("충북");
        Do_arrayList.add("경기도");

        Do_arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                Do_arrayList);

        Do = (Spinner)findViewById(R.id.Do);
        Do.setAdapter(Do_arrayAdapter);
        Do.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),Do_arrayList.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //-----------------------------------------Do_arrayList-----------------------------------------------//


        Si_arrayList = new ArrayList<>();
        Si_arrayList.add("천안");
        Si_arrayList.add("아산");
        Si_arrayList.add("청주");
        Si_arrayList.add("평택");
        Si_arrayList.add("계룡");
        Si_arrayList.add("논산");
        Si_arrayList.add("파주");

        Si_arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                Si_arrayList);

        Si = (Spinner)findViewById(R.id.Si);
        Si.setAdapter(Si_arrayAdapter);
        Si.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),Si_arrayList.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //-----------------------------------------Si_arrayList-----------------------------------------------//

        Attraction_arrayList = new ArrayList<>();
        Attraction_arrayList.add("독립기념관");
        Attraction_arrayList.add("계룡산");
        Attraction_arrayList.add("현충사");
        Attraction_arrayList.add("신정호 호수공원");


        Attraction_arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                Attraction_arrayList);

        Attraction = (Spinner)findViewById(R.id.Attraction);
        Attraction.setAdapter(Attraction_arrayAdapter);
        Attraction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),Attraction_arrayList.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //-----------------------------------------Attraction_arrayList-----------------------------------------------//






    }

    @Override
    public void onClick(View view) {

        if(R.id.select1 == view.getId()){
            new DatePickerDialog(LocalSearch.this, mDateSetListener, mYear, mMonth, mDay).show();

        }
        if(R.id.button3 == view.getId()){
            new TimePickerDialog(LocalSearch.this, mTimeSetListener, mHour, mMinute, false).show();
        }
        if(R.id.select2 == view.getId()){
            new DatePickerDialog(LocalSearch.this, mDateSetListener, mYear, mMonth, mDay).show();

        }
        if(R.id.button4 == view.getId()){
            new TimePickerDialog(LocalSearch.this, mTimeSetListener, mHour, mMinute, false).show();
        }

        if(R.id.searchgo == view.getId()) {        //검색버튼을 눌렀을 때
            Intent intent = new Intent(
                    getApplicationContext(),
                    pro.class
            );
            startActivity(intent);
        }

    }
    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    //사용자가 입력한 값을 가져온 뒤
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    UpdateNow();
                }
            };
    TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //사용자가 입력한 값을 가져온뒤
                    mHour = hourOfDay;
                    mMinute = minute;
                    UpdateNow();
                }
            };
    void UpdateNow() {
        textView1.setText(String.format("%d/%d/%d", mYear,mMonth + 1, mDay));
        textView2.setText(String.format("%d/%d/%d", mYear,mMonth + 1, mDay));
        time1.setText(String.format("%d:%d", mHour, mMinute));
        time2.setText(String.format("%d:%d", mHour, mMinute));
    }

}
