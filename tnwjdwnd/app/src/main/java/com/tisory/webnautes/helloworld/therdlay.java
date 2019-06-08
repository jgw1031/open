package com.tisory.webnautes.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class therdlay extends AppCompatActivity implements View.OnClickListener {

    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therdlay);

        Intent intent=getIntent();
        String s=intent.getStringExtra("data");
        TextView textView=(TextView)findViewById(R.id.textView6);
        TextView TITLEView=(TextView)findViewById(R.id.textView5);
        textView.setText(s);

        System.out.println(s);
        int indexAREA = s.indexOf("AREA");
        int indexGENDER = s.indexOf("GENDER");
        int indexTITLE = s.indexOf("TITLE");
        int indexCONTENTS = s.indexOf("CONTENTS");
        int indexTIME=s.indexOf("TIME");
        String TITLE = s.substring(indexTITLE+8,indexAREA); //TITLE값
        String CONTENT = s.substring(indexCONTENTS+11,indexTIME); //CONTENT값
        String TIME = s.substring(indexTIME+8);
        String GENDER = s.substring(indexGENDER+9);    //GENDER값
        String AREA = s.substring(indexAREA+7,indexCONTENTS);   //AREA값
        TITLEView.setText(TITLE);
        textView.setText(AREA+"\n"+TIME+"\n"+CONTENT);
        Button button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button9 = (Button)findViewById(R.id.button9);
        button9.setOnClickListener(this);
        Button button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        if(R.id.button5 == v.getId()) { //삭제
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(therdlay.this);
            alertDialogBuilder.setTitle("게시글 삭제");
            alertDialogBuilder.setMessage("정말 삭제하시겠습니까?");
            alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent cancle = new Intent(getApplicationContext(), pro.class);
                    startActivity(cancle);
                    finish();
                }

            });
            alertDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            alertDialogBuilder.show();
        }
        if(R.id.button8 == v.getId()) { //프로필보기
            Intent intent=getIntent();
            String id=intent.getStringExtra("id");
            Intent cancle = new Intent(getApplicationContext(), profile.class);
            cancle.putExtra("ID",ID);
            cancle.putExtra("id",id);
            System.out.println(ID+id);
            startActivity(cancle);
            finish();
        }
        if(R.id.button9 == v.getId()) {    //뒤로가기
            finish();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}