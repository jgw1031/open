package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button upbtn = (Button)findViewById(R.id.up);

        upbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(R.id.up == view.getId()) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    MainActivity.class);
            startActivity(intent);
        }
    }
}

