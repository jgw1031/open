package com.example.please;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class subactiviti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactiviti);
    }
    public void Cancle(View v)
    {
        Intent cancle= new Intent(this,MainActivity.class);
        startActivity(cancle);
    }
}
