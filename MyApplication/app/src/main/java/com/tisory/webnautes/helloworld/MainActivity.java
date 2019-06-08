package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

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
                    System.out.println(pw);
                    int indexId = pw.indexOf("id");
                    ID = pw.substring(indexId + 3, pw.indexOf("}"));
                    System.out.println(ID+id);
                    if(id.equals(id)) {
                        Toast.makeText(getApplicationContext(), "로그인!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(
                                getApplicationContext(),
                                SubActivity.class);
                        intent.putExtra("id", ID);
                        startActivity(intent);
                        finish();

                        break;
                    }
                    contine ++;
                    System.out.println(contine);
                }
                if(contine == dataSnapshot.getChildrenCount()) {
                    Intent intent = new Intent(
                            getApplicationContext(),
                            SignUp.class
                    );
                    startActivity(intent);
                    finish();
                }
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



