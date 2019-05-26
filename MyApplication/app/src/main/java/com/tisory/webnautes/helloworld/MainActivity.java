package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bin = (Button)findViewById(R.id.sin);
        bin.setOnClickListener(this);
        Button bup = (Button)findViewById(R.id.sup);
        bup.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        if(R.id.sin == view.getId()){
            Intent intent = new Intent(
                    getApplicationContext(),
                    SubActivity.class
            );
            startActivity(intent);
        }
        if(R.id.sup == view.getId()) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    SignUp.class
            );
            startActivity(intent);
        }
    }
}

