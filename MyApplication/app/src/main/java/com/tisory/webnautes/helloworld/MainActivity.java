package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText idtext;
    private EditText passtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bin = (Button)findViewById(R.id.sin);
        bin.setOnClickListener(this);
        Button bup = (Button)findViewById(R.id.sup);
        bup.setOnClickListener(this);
        idtext = (EditText)findViewById(R.id.log);
        passtext = (EditText)findViewById(R.id.pass);

    }


    @Override
    public void onClick(View view) {
        String id = idtext.getText().toString();
        String password = passtext.getText().toString();
        if(R.id.sin == view.getId()){
            if(id.equals("id") && password.equals("1234")){
                Intent intent = new Intent(
                        getApplicationContext(),
                        SubActivity.class
                );
                intent.putExtra("id",id);
                intent.putExtra("pass",password);
                startActivity(intent);
                setResult(RESULT_OK,intent);
                finish();
            }


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

