package com.tisory.webnautes.helloworld;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class profile extends AppCompatActivity {
    private static String TAG = "phpquerytest";
    private static final String TAG_JSON="Result";
    private static final String TAG_ID = "ID";
    private static final String TAG_NAME = "NAME";
    private static final String TAG_GENDER = "GENDER";
    private static final String TAG_PHONE = "PHONE";
    private static final String TAG_AGE = "AGE";
    private static final String TAG_country = "country";
    private static final String TAG_WRITER = "ID2";
    private static final String TAG_TEXT = "TEXT";
    private static final String TAG_STAR = "STAR";
    private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> personList;
    ListView mListViewList,mListViewList2;
    String mJsonString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        mListViewList2 = (ListView) findViewById(R.id.listView_review);
        Button button_search = (Button) findViewById(R.id.button_main_search);
        Intent intent=getIntent();
        final String id=intent.getStringExtra("id");
        final String ID=intent.getStringExtra("ID");
        GetData task = new GetData();
        task.execute(ID);
        personList = new ArrayList<HashMap<String, String>>();
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        review.class
                );
                intent.putExtra("id",id);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }
        });

    }


    private class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(profile.this,
                    "Please Wait", null, true, true);
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Log.d(TAG, "response-"+result);
            if(result.contains("{\"Result\":[")){
                mTextViewResult.setText(result);
                mJsonString = result;
                showResult();
            }
            else {
                mTextViewResult.setText(errorString);
            }

        }   //삭제 예정
        @Override
        protected String doInBackground(String... params) {
            String searchKeyword1 = params[0];
            String serverURL = "http://192.168.0.72/profile.php";
            String postParameters = "ID=" + searchKeyword1 ;
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
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString().trim();
            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                errorString = null;
                return null;
            }

        }
    }


    private void showResult(){
        try {
            System.out.println("JSON String 생성");
            JSONObject jsonObject = new JSONObject(mJsonString.substring(mJsonString.indexOf("{"), mJsonString.lastIndexOf("}") + 1));
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            for(int i=0;i<jsonArray.length();i++) {
                System.out.println(jsonArray);
                JSONObject item = jsonArray.getJSONObject(i);
                String ID = item.getString(TAG_ID);
                String NAME = item.getString(TAG_NAME);
                String GENDER = item.getString(TAG_GENDER);
                String PHONE = item.getString(TAG_PHONE);
                String AGE = item.getString(TAG_AGE);
                String STAR = item.getString(TAG_STAR);
                String WRITER = item.getString(TAG_WRITER);
                String TEXT = item.getString(TAG_TEXT);
                System.out.println(ID+NAME+GENDER+PHONE+AGE);
                TextView textView=(TextView)findViewById(R.id.profile);
                textView.setText("아이디 ="+ID+"\n이름 ="+NAME+"\n성별 ="+GENDER+"\n전화번호 = "+PHONE+"\n나이 ="+AGE);
                HashMap<String, String> persons = new HashMap<String, String>();
                persons.put(TAG_STAR,STAR);
                persons.put(TAG_WRITER,WRITER);
                persons.put(TAG_TEXT,TEXT);
                personList.add(persons);
            }
        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
        ListAdapter adapter1 = new SimpleAdapter(
                profile.this, personList, R.layout.item_review,
                new String[]{TAG_STAR,TAG_WRITER,TAG_TEXT},
                new int[]{R.id.STAR, R.id.WRITER,R.id.TEXT}
        );
        mListViewList2.setAdapter(adapter1);

    }

}