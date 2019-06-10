package com.tisory.webnautes.helloworld;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class therdlay extends AppCompatActivity implements View.OnClickListener {

    private String ID;
    private String TITLE;
    private String TAG = "therdlay ";
    private  final  String IP_ADDRESS = "211.225.70.184";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therdlay);

        Intent intent=getIntent();
        String s=intent.getStringExtra("text");
        TextView textView=(TextView)findViewById(R.id.textView6);
        TextView TITLEView=(TextView)findViewById(R.id.textView5);
        textView.setText(s);
        int indexNO = s.indexOf("NO");
        int indexAREA = s.indexOf("AREA");
        int indexGENDER = s.indexOf("GENDER");
        int indexTITLE = s.indexOf("TITLE");
        int indexID = s.indexOf("ID");
        int indexCONTENTS = s.indexOf("CONTENTS");
        int indexTIME=s.indexOf("TIMES");
        TITLE = s.substring(indexTITLE+6, indexCONTENTS-2); //TITLE값
        String CONTENT = s.substring(indexCONTENTS+9,indexTIME-2); //CONTENT값
        String TIME = s.substring(indexTIME+6,indexID-2);
        ID = s.substring(indexID+3, indexNO-2); //ID값
        String NO = s.substring(indexNO+3, indexGENDER-2);     //NO값
        String GENDER = s.substring(indexGENDER+7, indexAREA-2);    //GENDER값
        String AREA = s.substring(indexAREA+5, s.indexOf("}"));   //AREA값
        TITLEView.setText(NO+" "+TITLE);
        textView.setText(AREA+"\n"+GENDER+"\n"+CONTENT);
        Button apply = (Button)findViewById(R.id.apply);
        apply.setOnClickListener(this);
        Button button9 = (Button)findViewById(R.id.button9);
        button9.setOnClickListener(this);
        Button button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        if(R.id.apply == v.getId()) { //삭제
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(therdlay.this);
            alertDialogBuilder.setTitle("가이드 신청");
            alertDialogBuilder.setMessage("정말 신청하시겠습니까?");
            alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    NotificationManager notificationManager= (NotificationManager)therdlay.this.getSystemService(therdlay.this.NOTIFICATION_SERVICE);
                    Intent intent1 = new Intent(therdlay.this.getApplicationContext(),pro.class); //인텐트 생성.
                    Notification.Builder builder = new Notification.Builder(getApplicationContext());
                    intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    PendingIntent pendingNotificationIntent = PendingIntent.getActivity( therdlay.this,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark).setTicker("HETT").setWhen(System.currentTimeMillis())
                            .setNumber(1).setContentTitle(TITLE).setContentText("신청완료")
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingNotificationIntent).setAutoCancel(true).setOngoing(true);
                    notificationManager.notify(1, builder.build()); // Notification send
                    therdlay.InsertData task = new therdlay.InsertData();
                    task.execute("http://" + IP_ADDRESS + "/push.php",ID,"a");
                    finish();
                }

            });
            alertDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent cancle = new Intent(getApplicationContext(), pro.class);
                    startActivity(cancle);
                    dialogInterface.cancel();
                }

            });
            alertDialogBuilder.show();
        }
        if(R.id.button8 == v.getId()) { //프로필보기
            Intent intent=getIntent();
            String id=intent.getStringExtra("id");
            Intent cancle = new Intent(getApplicationContext(), profile.class);
            cancle.putExtra("ID",ID);
            cancle.putExtra("id",id);
            startActivity(cancle);
            finish();
        }
        if(R.id.button9 == v.getId()) {    //뒤로가기
            finish();
        }
    }
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(therdlay.this,
                    "Please Wait", null, true, true);
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }
        @Override
        protected String doInBackground(String... params) {
            String ID = (String)params[1];
            String TOKEN = (String) params[2];
            String serverURL = (String)params[0];
            String postParameters = "ID="+ID+"&TOKEN="+TOKEN;
            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();
                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);
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
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString();
            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                return new String("Error: " + e.getMessage());
            }
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}