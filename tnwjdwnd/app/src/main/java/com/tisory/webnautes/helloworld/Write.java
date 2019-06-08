package com.tisory.webnautes.helloworld;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Write extends AppCompatActivity {
    private static String IP_ADDRESS = "118.34.34.178";
    private static String TAG = "Write";
    private DatabaseReference mPostReference;
    private EditText mEditTextTITLE;
    private EditText mEditTextCONTENTS;
    private EditText mEditTextAREA;
    private EditText mEditTextTIME;
    public String TITLE ;
    public String CONTENTS ;
    public String AREA ;
    public String TIME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        mEditTextTITLE = (EditText)findViewById(R.id.multiAutoCompleteTextView);
        mEditTextCONTENTS = (EditText)findViewById(R.id.multiAutoCompleteTextView2);
        mEditTextAREA = (EditText)findViewById(R.id.multiAutoCompleteTextView3);
        mEditTextTIME = (EditText)findViewById(R.id.multiAutoCompleteTextView4);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button3 = (Button)findViewById(R.id.button3);
        Intent con=getIntent();
        final String ID =con.getStringExtra("id");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Write.this);
                alertDialogBuilder.setTitle("게시글 등록");
                alertDialogBuilder.setMessage("정말 취소하시겠습니까?");
                alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent cancle = new Intent(getApplicationContext(), Search.class);
                        cancle.putExtra("id",ID);
                        startActivity(cancle);
                        finish();
                    }

                });
                alertDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialogBuilder.show();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 TITLE = mEditTextTITLE.getText().toString();
                 CONTENTS = mEditTextCONTENTS.getText().toString();
                 AREA = mEditTextAREA.getText().toString();
                 TIME = mEditTextTIME.getText().toString();
                System.out.println(TITLE+CONTENTS+AREA+TIME);
                postFirebaseDatabase(true);
                mEditTextTITLE.setText("");
                mEditTextCONTENTS.setText("");
                if(TITLE.equals("") || CONTENTS.equals("") || AREA.equals("") || TIME.equals("")) {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Write.this);
                    alertDialogBuilder.setTitle("게시글 등록");
                    alertDialogBuilder.setMessage("게시글등록이 실패되었습니다.");
                    alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent cancle = new Intent(getApplicationContext(), Write.class);
                            cancle.putExtra("id",ID);
                            startActivity(cancle);
                            finish();
                        }

                    });
                    alertDialogBuilder.show();
                }
                else {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Write.this);
                    alertDialogBuilder.setTitle("게시글 등록");
                    alertDialogBuilder.setMessage("게시글등록이 완료되었습니다.");
                    alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent cancle = new Intent(getApplicationContext(), Search.class);
                            cancle.putExtra("id", ID);
                            startActivity(cancle);
                            finish();
                        }

                    });
                    alertDialogBuilder.show();
                }
            }
        });

    }

    public void postFirebaseDatabase(boolean add){
        mPostReference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        String ID = FirebaseInstanceId.getInstance().getToken();
        if(add){
            com.tisory.webnautes.helloworld.FirebasePost post = new  com.tisory.webnautes.helloworld.FirebasePost(ID,TITLE,CONTENTS,AREA,TIME);
            postValues = post.toMap();
        }
        childUpdates.put("/board/" + AREA, postValues);
        mPostReference.updateChildren(childUpdates);
    }
}

