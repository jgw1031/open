package com.example.please;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void btn1(View v)
    {
        Intent intent1= new Intent(this,subactiviti.class);
        startActivity(intent1);
    }
    public void btn2(View v)
    {
        Intent intent2 = new Intent(this,therdlay.class);
        startActivity(intent2);
    }

}
