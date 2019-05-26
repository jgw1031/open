package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Guide extends Activity implements View.OnClickListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_guide);

            Button post = (Button)findViewById(R.id.post);
            post.setOnClickListener(this);
            Button userbtn = (Button)findViewById(R.id.userbtn);
            userbtn.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        if(R.id.post == view.getId()){
            Intent intent = new Intent(
                    getApplicationContext(),
                    pro.class);
            startActivity(intent);
        }
        if(R.id.userbtn == view.getId()){
            Intent intent = new Intent(
                    getApplicationContext(),
                    SubActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
