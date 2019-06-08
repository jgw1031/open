package com.tisory.webnautes.helloworld;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.tisory.webnautes.helloworld.FirebasePost;
import com.tisory.webnautes.helloworld.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pro  extends AppCompatActivity implements View.OnClickListener{

    Button btn_Select;
    EditText edit_ID;
    EditText edit_Name;

    String sort = "id";

    ArrayAdapter<String> arrayAdapter;

    static ArrayList<String> arrayIndex =  new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        btn_Select = (Button) findViewById(R.id.button_main_search);
        btn_Select.setOnClickListener(this);
        edit_ID = (EditText) findViewById(R.id.editText_main_searchKeyword1);
        edit_Name = (EditText) findViewById(R.id.editText_main_searchKeyword2);

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.itemlist);
        ListView listView = (ListView) findViewById(R.id.listView_main_list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(onClickListener);

        getFirebaseDatabase();
    }


    private AdapterView.OnItemClickListener onClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("On Click", "position = " + position);
            Log.e("On Click", "Data: " + arrayData.get(position));
            String[] tempData = arrayData.get(position).split("\\s+");
            Log.e("On Click", "Split Result = " + tempData);
            String data=arrayData.get(position);
            System.out.println(data);
            Intent intent = new Intent(
                    getApplicationContext(),
                    therdlay.class);
            intent.putExtra("data", data);
            startActivity(intent);
            finish();
        }
    };

    public void getFirebaseDatabase(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("getFirebaseDatabase", "key: " + dataSnapshot.getChildrenCount());
                arrayData.clear();
                arrayIndex.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    firebs get = postSnapshot.getValue(firebs.class);
                    String[] info = {get.TITLE, get.AREA, get.CONTENTS,get.TIME};
                    System.out.println(info[3]+"/"+info[2]+"?"+info[1]+"2"+info[0]);
                    String Result = setTextLength("TITLE = "+info[0]+"\n",10) + setTextLength("AREA = "+info[1]+"\n",10) + setTextLength("CONTENTS = "+info[2]+"\n",10) + setTextLength("TIME = "+info[3]+"\n",10);
                    arrayData.add(Result);
                    arrayIndex.add(key);
                    Log.d("getFirebaseDatabase", "key: " + key);
                    Log.d("getFirebaseDatabase", "info: " + info[0] + info[1] + info[2] + info[3]);
                }
                arrayAdapter.clear();
                arrayAdapter.addAll(arrayData);
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("getFirebaseDatabase","loadPost:onCancelled", databaseError.toException());
            }
        };
        Query sortbyAge = FirebaseDatabase.getInstance().getReference().child("board").orderByChild("TIME");
        sortbyAge.addListenerForSingleValueEvent(postListener);
    }
    public String setTextLength(String text, int length){
        if(text.length()<length){
            int gap = length - text.length();
            for (int i=0; i<gap; i++){
                text = text + " ";
            }
        }
        return text;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select:
                getFirebaseDatabase();
                break;
        }
    }
}
