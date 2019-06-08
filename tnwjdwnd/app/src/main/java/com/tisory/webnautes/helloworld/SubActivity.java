package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button local = (Button)findViewById(R.id.searchbtn);
        local.setOnClickListener(this);
        Button guidebtn = (Button)findViewById(R.id.Guidebtn);
        guidebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(R.id.searchbtn == view.getId()){
            Intent con = getIntent();
            String id = con.getStringExtra("id");
            System.out.println(id);
            Intent intent = new Intent(
                    getApplicationContext(),
                    LocalSearch.class);
            intent.putExtra("id",id);
            startActivity(intent);
        }

        if(R.id.Guidebtn == view.getId()){
            Intent con = getIntent();
            String id = con.getStringExtra("id");
            Intent intent = new Intent(
                    getApplicationContext(),
                    Guide.class);
            intent.putExtra("id",id);
            startActivity(intent);
            finish();
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
            System.runFinalization();
            System.exit(0);
            return;
        }
        //'뒤로' 버튼 한번 클릭 시 메시지
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        //lastTimeBackPressed에 '뒤로'버튼이 눌린 시간을 기록
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
