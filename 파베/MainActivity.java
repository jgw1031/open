package com.tisory.webnautes.helloworld;

import android.app.Activity;
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
import com.google.firebase.database.*;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener {
    static ArrayList<String> arrayIndex = new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();
    private EditText idtext;
    private EditText passtext;
    public String line;
    private String loginx;
    private DatabaseReference databaseReference;
    String ID;
    public int contine=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bin = (Button) findViewById(R.id.sin);
        bin.setOnClickListener(this);
        Button bup = (Button) findViewById(R.id.sup);
        bup.setOnClickListener(this);
        idtext = (EditText) findViewById(R.id.log);
        passtext = (EditText) findViewById(R.id.pass);
        databaseReference = FirebaseDatabase.getInstance().getReference("id_list");
        getFirebaseDatabase();
        System.out.println(contine);
        if(contine == 2) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    SignUp.class
            );
            startActivity(intent);
            finish();
        }
    }



    public void getFirebaseDatabase() {
        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("getFirebaseDatabase", "key: " + dataSnapshot.getChildrenCount());
                arrayData.clear();
                arrayIndex.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String id = FirebaseInstanceId.getInstance().getToken();
                    databaseReference.child("id_list").getDatabase();
                    String key = postSnapshot.getKey();
                    String pw = postSnapshot.getValue().toString();
                    arrayIndex.add(key);
                    int indexId = pw.indexOf("id");
                    ID = pw.substring(indexId + 3, pw.indexOf("}"));
                    if(id.equals(ID)) {
                        Toast.makeText(getApplicationContext(), "로그인!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(
                                getApplicationContext(),
                                SubActivity.class);
                        intent.putExtra("id", ID);
                        startActivity(intent);
                        finish();
                        contine=1;
                    }
                }
                contine=2;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("getFirebaseDatabase", "loadPost:onCancelled", databaseError.toException());
            }
        };
        Query sortbyAge = FirebaseDatabase.getInstance().getReference().child("id_list").orderByChild("id");
        sortbyAge.addListenerForSingleValueEvent(postListener);
    }


    @Override
    public void onClick(View view) {


    }
}



