package com.tisory.webnautes.helloworld;

import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.FragmentManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.ArrayList;

public class LocalSearch extends AppCompatActivity implements View.OnClickListener,OnMapReadyCallback {
    private GoogleMap mMap;
    private Spinner Do;
    public String DoString;
    private Spinner Si;
    public String SiString;
    private Spinner Attraction;
    public String AttractionString;
    private String DataM1,DataM2,TimeM1,TimeM2;
    private MapView mapView = null;
    int mYear, mMonth, mDay, mHour, mMinute;
    int mYearm, mMonthm, mDaym, mHourm, mMinutem;
    TextView textView1;
    TextView textView2;
    TextView time1;
    TextView time2;
    EditText searchgender;
    EditText searchid;
    ArrayList<String> Do_arrayList;
    ArrayAdapter<String> Do_arrayAdapter;
    ArrayList<String> Si_arrayList;
    ArrayAdapter<String> Si_arrayAdapter;
    ArrayList<String> Attraction_arrayList;
    ArrayList<LatLng> Attraction_arrayListpro;
    ArrayAdapter<String> Attraction_arrayAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localsearch);
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        searchgender = (EditText)findViewById(R.id.searchgender);
        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);
        mYearm = cal.get(Calendar.YEAR);
        mMonthm = cal.get(Calendar.MONTH);
        mDaym = cal.get(Calendar.DAY_OF_MONTH);
        mHourm = cal.get(Calendar.HOUR_OF_DAY);
        mMinutem = cal.get(Calendar.MINUTE);
        UpdateNow();
        Attraction_arrayList = new ArrayList<>();
        Attraction_arrayListpro = new ArrayList<>();
        Attraction_arrayList.add(" ");
        LatLng SEOULs = new LatLng(36.8090768,127.1023921);
        Attraction_arrayListpro.add(SEOULs);
        Attraction_arrayList.add("계룡산");
        LatLng SEOUL = new LatLng(36.6939717,127.0342684);
        Attraction_arrayListpro.add(SEOUL);
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
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Attraction_arrayListpro.get(i)));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
                AttractionString=Attraction_arrayList.get(i).toString();
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
            new DatePickerDialog(LocalSearch.this, mDateSetListenerm, mYearm, mMonthm, mDaym).show();
        }
        if(R.id.button4 == view.getId()){
            new TimePickerDialog(LocalSearch.this, mTimeSetListenerm, mHourm, mMinutem, false).show();
        }
        if(R.id.searchgo == view.getId()) {        //검색버튼을 눌렀을 때
            Intent con = getIntent();
            String id = con.getStringExtra("id");
            Intent intent = new Intent(getApplicationContext(),pro.class);
            String AREA =(DoString+SiString+AttractionString);
            String Data1=(DataM1);
            String Time1=(TimeM1);
            String Data2=(DataM2);
            String Time2=(TimeM2);
            String GENDER = searchgender.getText().toString();
            intent.putExtra("id",id);
            intent.putExtra("time1",Time1);
            intent.putExtra("time2",Time2);
            intent.putExtra("data1",Data1);
            intent.putExtra("data2",Data2);
            intent.putExtra("area",AREA);
            intent.putExtra("GENDER",GENDER);
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
    DatePickerDialog.OnDateSetListener mDateSetListenerm =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    //사용자가 입력한 값을 가져온 뒤
                    mYearm = year;
                    mMonthm = monthOfYear;
                    mDaym = dayOfMonth;
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
    TimePickerDialog.OnTimeSetListener mTimeSetListenerm =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //사용자가 입력한 값을 가져온뒤
                    mHourm = hourOfDay;
                    mMinutem = minute;
                    UpdateNow();
                }
            };
    void UpdateNow() {
        DataM1 = String.format("%d/%d/%d", mYear,mMonth + 1, mDay);
        DataM2 = String.format("%d/%d/%d", mYearm,mMonthm + 1, mDaym);
        TimeM1 = String.format("%d:%d", mHour, mMinute);
        TimeM2 = String.format("%d:%d", mHourm, mMinutem);

        textView1.setText(String.format("%d/%d/%d", mYear,mMonth + 1, mDay));
        textView2.setText(String.format("%d/%d/%d", mYearm,mMonthm + 1, mDaym));
        time1.setText(String.format("%d:%d", mHour, mMinute));
        time2.setText(String.format("%d:%d", mHourm, mMinutem));
    }

    @Override
    public void onMapReady(GoogleMap map) {


        LatLng SEOUL = new LatLng(36.8090768,127.1023921);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("기준");
        markerOptions.snippet("중앙설정 ");
        map.addMarker(markerOptions);
        mMap = map;
        for (int i = 0; i < 10; i++) {
            // 1. 마커 옵션 설정 (만드는 과정)
            MarkerOptions makerOptions = new MarkerOptions();
            makerOptions.position(new LatLng(36.6939717+ i, 127.0342684));
            makerOptions.title("마커" + i); // 타이틀.
            // 2. 마커 생성 (마커를 나타냄)
            mMap.addMarker(makerOptions);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(36.8090768,127.1023921)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }
}