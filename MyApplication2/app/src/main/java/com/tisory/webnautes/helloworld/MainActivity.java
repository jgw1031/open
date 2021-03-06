package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText idtext;
    private EditText passtext;
    public String line;
    private String loginx;

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
    private class GetData extends AsyncTask<String, Void, String> {
        String errorString = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            
            String searchKeyword1 = params[0];
            String searchKeyword2 = params[1];
            String serverURL = "http://211.225.70.184/login.php";
            String postParameters = "ID=" + searchKeyword1 +"&PASSWORD=" + searchKeyword2  ;
            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();
                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);
                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String id = idtext.getText().toString();
                String password = passtext.getText().toString();
                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                String result = sb.substring(sb.lastIndexOf("/>")+2);
                if(result.equals("successb")){
                    Intent intent = new Intent(
                            getApplicationContext(),
                            SubActivity.class
                    );
                    intent.putExtra("id",id);
                    intent.putExtra("pass",password);
                    finish();
                    startActivity(intent);
                    setResult(RESULT_OK,intent);

                }
                else if(result.equals("successa")){
                    NotificationManager notificationManager= (NotificationManager)MainActivity.this.getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
                    Intent intent1 = new Intent(MainActivity.this.getApplicationContext(),Board.class); //인텐트 생성.
                    Notification.Builder builder = new Notification.Builder(getApplicationContext());
                    intent1.putExtra("id",id);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    PendingIntent pendingNotificationIntent = PendingIntent.getActivity( MainActivity.this,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark).setTicker("HETT").setWhen(System.currentTimeMillis())
                            .setNumber(1).setContentTitle("새로운 매칭 ").setContentText("가이드 신청자가 있습니다 ")
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingNotificationIntent).setAutoCancel(true).setOngoing(true);
                    notificationManager.notify(1, builder.build()); // Notification send
                    
                    Intent intent = new Intent(
                            getApplicationContext(),
                            SubActivity.class
                    );
                    intent.putExtra("id",id);
                    intent.putExtra("pass",password);
                    finish();
                    startActivity(intent);
                    setResult(RESULT_OK,intent);

                }
                else{
                    Intent intent = new Intent(
                            getApplicationContext(),
                            MainActivity.class
                    );
                    startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this, "아이디와 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                bufferedReader.close();
                return null;
            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = null;

                return null;
            }

        }
    }
    @Override
    public void onClick(View view) {

        if(R.id.sin == view.getId()){
            MainActivity.GetData task = new MainActivity.GetData();
            task.execute(idtext.getText().toString(), passtext.getText().toString());
           // if(loginx.equals("op"))

        }
        if(R.id.sup == view.getId()) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    SignUp.class
            );
            startActivity(intent);
            finish();
        }
    }

}

