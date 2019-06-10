package com.tisory.webnautes.helloworld;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class therdlay extends AppCompatActivity implements View.OnClickListener {

    private String ID;
    private String TITLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therdlay);

        Intent intent=getIntent();
        String s=intent.getStringExtra("text");
        TextView textView=(TextView)findViewById(R.id.textView6);
        TextView TITLEView=(TextView)findViewById(R.id.textView5);
        textView.setText(s);
        System.out.println(s);
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
            alertDialogBuilder.setTitle("게시글 삭제");
            alertDialogBuilder.setMessage("정말 삭제하시겠습니까?");
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
            System.out.println(ID+id);
            startActivity(cancle);
            finish();
        }
        if(R.id.button9 == v.getId()) {    //뒤로가기
            finish();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}