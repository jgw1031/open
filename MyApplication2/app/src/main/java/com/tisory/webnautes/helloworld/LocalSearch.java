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
    public GoogleMap mMap  ;
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
        LatLng bla = new LatLng(36.8090768,127.1023921);
        Attraction_arrayListpro.add(bla);

        Attraction_arrayList.add("광덕산");
        LatLng deok = new LatLng(36.6939717,127.0342684);
        Attraction_arrayListpro.add(deok);

        Attraction_arrayList.add("유관순열사 사적지");
        LatLng yu = new LatLng(36.7594749,127.30802);
        Attraction_arrayListpro.add(yu);

        Attraction_arrayList.add("각원사");
        LatLng gak = new LatLng(36.834157,127.196966);
        Attraction_arrayListpro.add(gak);

        Attraction_arrayList.add("광덕사");
        LatLng gwang = new LatLng(36.675708,127.042267);
        Attraction_arrayListpro.add(gwang);

        Attraction_arrayList.add("독립기념관");
        LatLng dok = new LatLng(36.7783072,127.2308099);
        Attraction_arrayListpro.add(dok);

        Attraction_arrayList.add("테딘워터파크");
        LatLng tedin = new LatLng(36.757092,127.223096);
        Attraction_arrayListpro.add(tedin);

        Attraction_arrayList.add("상록리조트");
        LatLng sangrok = new LatLng(36.739159,127.288349);
        Attraction_arrayListpro.add(sangrok);

        Attraction_arrayList.add("천안삼거리");
        LatLng samstreet = new LatLng(36.783373,127.169065);
        Attraction_arrayListpro.add(samstreet);

        Attraction_arrayList.add("아라리오광장");
        LatLng arario = new LatLng(36.819251,127.156467);
        Attraction_arrayListpro.add(arario);

        Attraction_arrayList.add("병천순대거리");
        LatLng sundae = new LatLng(36.759748,127.297656);
        Attraction_arrayListpro.add(sundae);


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
        mMap = map;
        LatLng deok = new LatLng(36.6939717,127.0342684);   //광덕산의 마커
        MarkerOptions markerOptionsdeok = new MarkerOptions();
        markerOptionsdeok.position(deok);
        markerOptionsdeok.title("광덕산");
        markerOptionsdeok.snippet("광덕산은 해발 699m로 전국에 잘 알려진 100대 명산");
        map.addMarker(markerOptionsdeok);


        LatLng yu = new LatLng(36.7594749,127.30802);       //유관순열사 사적지의 마커
        MarkerOptions markerOptionsyu = new MarkerOptions();
        markerOptionsyu.position(yu);
        markerOptionsyu.title("유관순열사 사적지");
        markerOptionsyu.snippet("유관순열사 기념관은 열사의 탄신 100주년을 기념한 사적지");
        map.addMarker(markerOptionsyu);


        LatLng gak = new LatLng(36.834157,127.196966);      //각원사의 마커
        MarkerOptions markerOptionsgak = new MarkerOptions();
        markerOptionsgak.position(gak);
        markerOptionsgak.title("각원사");
        markerOptionsgak.snippet("남북통일을 기원하기 위해 만든 청동대불이 유명");
        map.addMarker(markerOptionsgak);


        LatLng gwang = new LatLng(36.675708,127.042267);        //광덕사 마커
        MarkerOptions markerOptionsgwang = new MarkerOptions();
        markerOptionsgwang.position(gwang);
        markerOptionsgwang.title("광덕사");
        markerOptionsgwang.snippet("신라 27대 선덕여왕때 자장율사가 창건하고 진산대사가 중건한 절");
        map.addMarker(markerOptionsgwang);


        LatLng dok = new LatLng(36.7783072,127.2308099);        //독립기념관 마커
        MarkerOptions markerOptionsdok = new MarkerOptions();
        markerOptionsdok.position(dok);
        markerOptionsdok.title("독립기념관");
        markerOptionsdok.snippet("대한민국을 물려주신 선열들의 빛나는 역사를 기록하고 있는 장소");
        map.addMarker(markerOptionsdok);


        LatLng tedin = new LatLng(36.757092,127.223096);        //테딘 워터파크 마커
        MarkerOptions markerOptionstedin = new MarkerOptions();
        markerOptionstedin.position(tedin);
        markerOptionstedin.title("테딘워터파크");
        markerOptionstedin.snippet("충남 천안에 위치한 테딘워터파크&스파는 수질이 좋기로 유명한 곳");
        map.addMarker(markerOptionstedin);


        LatLng sangrok = new LatLng(36.739159,127.288349);      //상록리조트 마커
        MarkerOptions markerOptionssangrok = new MarkerOptions();
        markerOptionssangrok.position(sangrok);
        markerOptionssangrok.title("상록리조트");
        markerOptionssangrok.snippet("어린이 놀이시설, 골프장, 아쿠아피아 등 다양한 시설을 갖춘 장소");
        map.addMarker(markerOptionssangrok);


        LatLng samstreet = new LatLng(36.783373,127.169065);    //천안삼거리 마커
        MarkerOptions markerOptionssamstreet = new MarkerOptions();
        markerOptionssamstreet.position(samstreet);
        markerOptionssamstreet.title("천안삼거리");
        markerOptionssamstreet.snippet(" 해마다 흥타령 축제등 많은 문화행사가 다채롭게 열림");
        map.addMarker(markerOptionssamstreet);


        LatLng arario = new LatLng(36.819251,127.156467);       //아라리오 광장 마커
        MarkerOptions markerOptionsarario = new MarkerOptions();
        markerOptionsarario.position(arario);
        markerOptionsarario.title("아라리오광장");
        markerOptionsarario.snippet("현대미술 거장의 작품을 감상할 수 있는 천안 아라리오 광장");
        map.addMarker(markerOptionsarario);


        LatLng sundae = new LatLng(36.759748,127.297656);   //병천순대거리 마커
        MarkerOptions markerOptionssundae = new MarkerOptions();
        markerOptionssundae.position(sundae);
        markerOptionssundae.title("병천순대거리");
        markerOptionssundae.snippet("아우내 병천 장터에는 수십곳의 순대 전문점이 있는 거리");
        map.addMarker(markerOptionssundae);
    }
}