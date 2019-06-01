package com.tisory.webnautes.helloworld;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class Write extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        if(R.id.button4 == v.getId()) {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Write.this);
            alertDialogBuilder.setTitle("게시글 등록");
            alertDialogBuilder.setMessage("정말 취소하시겠습니까?");
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
        else if(R.id.button3 == v.getId()) {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Write.this);
            alertDialogBuilder.setTitle("게시글 등록");
            alertDialogBuilder.setMessage("게시글등록이 완료되었습니다.");
            alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent cancle = new Intent(getApplicationContext(), pro.class);
                    startActivity(cancle);
                    finish();
                }

            });

        }
    }
}