package com.tisory.webnautes.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Write extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
    }
    public void Cancle(View v)
    {
        Intent cancle= new Intent(this,pro.class);
        startActivity(cancle);
    }
}