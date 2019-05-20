package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button local = (Button)findViewById(R.id.searchbtn);
        local.setOnClickListener(this);
        Button board = (Button)findViewById(R.id.boardbtn);
        board.setOnClickListener(this);
        Button guidebtn = (Button)findViewById(R.id.Guidebtn);
        guidebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(R.id.searchbtn == view.getId()){
            Intent intent = new Intent(
                    getApplicationContext(),
                    LocalSearch.class);
            startActivity(intent);
        }
        if(R.id.boardbtn == view.getId()){
            Intent intent = new Intent(
                    getApplicationContext(),
                    Board.class);
            startActivity(intent);
        }
        if(R.id.Guidebtn == view.getId()){
            Intent intent = new Intent(
                    getApplicationContext(),
                    Guide.class);

            startActivity(intent);
        }
    }
}
