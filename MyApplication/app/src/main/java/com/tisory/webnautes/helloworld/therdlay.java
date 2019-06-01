package com.tisory.webnautes.helloworld;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class therdlay extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therdlay);

        Intent intent=getIntent();
        String s=intent.getStringExtra("text");
        TextView textView=(TextView)findViewById(R.id.textView6);
        textView.setText(s);
        String str = "NO=2,AREA=area,GENDER=gender,TITLE=title,ID=id,CONTENTS=contents";

        String[] arr = str.split("=");
        String s1 ="";
        String s2 ="";
        for(int i=0; i<arr.length; i++) {
            s1=s1+arr[i];
        }
        String[] arr2 = s.split(",");
        for(int i=0; i<arr2.length; i++) {
            s2 = s2+arr2[i];
        }
        int indexNO = s2.indexOf("NO");
        int indexAREA = s2.indexOf("AREA");
        int indexGENDER = s2.indexOf("GENDER");
        int indexTITLE = s2.indexOf("TITLE");
        int indexID = s2.indexOf("ID");
        int indexCONTENTS = s2.indexOf("CONTENTS");
        String NO = s2.substring(indexNO+2, indexAREA);     //NO값
        String AREA = s2.substring(indexAREA+4, indexGENDER);   //AREA값
        String GENDER = s2.substring(indexGENDER+6, indexTITLE);    //GENDER값
        String TITLE = s2.substring(indexTITLE+5, indexID); //TITLE값
        String ID = s2.substring(indexID+2, indexCONTENTS); //ID값
        String CONTENT = s2.substring(indexCONTENTS+8); //CONTENT값


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
            alertDialogBuilder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent cancle = new Intent(getApplicationContext(), pro.class);
                    startActivity(cancle);
                    finish();
                }

            });
        }
        if(R.id.button8 == v.getId()) { //프로필보기
            Intent cancle = new Intent(getApplicationContext(), pro.class);
            startActivity(cancle);
            finish();
        }
        if(R.id.button9 == v.getId()) {    //뒤로가기
            Intent cancle = new Intent(getApplicationContext(), pro.class);
            startActivity(cancle);
            finish();
        }
    }

}