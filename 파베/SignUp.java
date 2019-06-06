package com.tisory.webnautes.helloworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mPostReference;

    private EditText mEditTextID;
    private EditText mEditTextNAME;
    private EditText mEditTextPASSWORD;
    private EditText mEditTextAGE;
    private EditText mEditTextPHONE;
    private EditText mEditTextGENDER;

    String ID;
    String name;
    String  pw;
    String gender = "";
    String phoen;
    String age;
    String sort = "id";

    ArrayAdapter<String> arrayAdapter;

    static ArrayList<String> arrayIndex =  new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mEditTextID = (EditText)findViewById(R.id.editText_main_ID);
        mEditTextNAME = (EditText)findViewById(R.id.editText_main_NAME);
        mEditTextPASSWORD = (EditText)findViewById(R.id.editText_main_PASSWORD);
        mEditTextAGE = (EditText)findViewById(R.id.editText_main_AGE);
        mEditTextPHONE = (EditText)findViewById(R.id.editText_main_PHONE);
        mEditTextGENDER = (EditText)findViewById(R.id.editText_main_GENDER);
        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                    name = mEditTextNAME.getText().toString();
                                                    pw = mEditTextPASSWORD.getText().toString();
                                                    age = mEditTextAGE.getText().toString();
                                                    phoen = mEditTextPHONE.getText().toString();
                                                    gender = mEditTextGENDER.getText().toString();
                                                        postFirebaseDatabase(true);
                                                        getFirebaseDatabase();
                                                        Toast.makeText(SignUp.this, "가입 완료되었습니다", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(
                                                                getApplicationContext(),
                                                                MainActivity.class
                                                        );
                                                        startActivity(intent);
                                                        finish();

                                                    mEditTextID.requestFocus();
                                                    mEditTextID.setCursorVisible(true);
                                            }
                                        });
        getFirebaseDatabase();
    }

    public void postFirebaseDatabase(boolean add){
        mPostReference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        String ID = FirebaseInstanceId.getInstance().getToken();
        if(add){
            com.tisory.webnautes.helloworld.FirebasePost post = new  com.tisory.webnautes.helloworld.FirebasePost(ID, name, pw, gender,phoen,age);
            postValues = post.toMap();
        }
        childUpdates.put("/id_list/" + ID, postValues);
        mPostReference.updateChildren(childUpdates);
    }

    public void getFirebaseDatabase(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("getFirebaseDatabase", "key: " + dataSnapshot.getChildrenCount());
                arrayData.clear();
                arrayIndex.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    arrayIndex.add(key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("getFirebaseDatabase","loadPost:onCancelled", databaseError.toException());
            }
        };
        Query sortbyAge = FirebaseDatabase.getInstance().getReference().child("id_list").orderByChild(sort);
        sortbyAge.addListenerForSingleValueEvent(postListener);
    }

    public boolean IsExistID(){
        boolean IsExist = arrayIndex.contains(ID);
        return IsExist;
    }


    @Override
    public void onClick(View v) {

    }
}
