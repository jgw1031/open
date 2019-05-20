package com.tisory.webnautes.helloworld;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LocalSearch extends AppCompatActivity {
    private Spinner Do;
    private Spinner Si;
    private Spinner Attraction;
    private Spinner Sex;
    ArrayList<String> Do_arrayList;
    ArrayAdapter<String> Do_arrayAdapter;

    ArrayList<String> Si_arrayList;
    ArrayAdapter<String> Si_arrayAdapter;

    ArrayList<String> Attraction_arrayList;
    ArrayAdapter<String> Attraction_arrayAdapter;

    ArrayList<String> Sex_arrayList;
    ArrayAdapter<String> Sex_arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localsearch);
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

        Sex_arrayList = new ArrayList<>();
        Sex_arrayList.add("남");
        Sex_arrayList.add("여");


        Sex_arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                Sex_arrayList);

        Sex = (Spinner)findViewById(R.id.Sex);
        Sex.setAdapter(Sex_arrayAdapter);
        Sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),Sex_arrayList.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //-----------------------------------------Sex_arrayList-----------------------------------------------//
    }
}
