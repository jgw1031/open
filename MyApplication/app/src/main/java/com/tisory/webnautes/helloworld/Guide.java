package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        }
    }
    private long lastTimeBackPressed; //뒤로가기 버튼이 클릭된 시간
    @Override
    public void onBackPressed()
    {
        //2초 이내에 뒤로가기 버튼을 재 클릭 시 앱 종료
        if (System.currentTimeMillis() - lastTimeBackPressed < 2000)
        {
            finish();
            return;
        }
        //'뒤로' 버튼 한번 클릭 시 메시지
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        //lastTimeBackPressed에 '뒤로'버튼이 눌린 시간을 기록
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
