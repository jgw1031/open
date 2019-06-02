package com.tisory.webnautes.helloworld;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Adapter.*;
import android.widget.Toast;

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


public class pro extends AppCompatActivity {
    private static String TAG = "phpquerytest";
    private static final String TAG_JSON="Result";
    private static final String TAG_ID = "ID";
    private static final String TAG_NO = "NO";
    private static final String TAG_AREA = "AREA";
    private static final String TAG_GENDER = "GENDER";
    private static final String TAG_TITLE = "TITLE";
    private static final String TAG_CONTENTS = "CONTENTS";
    private static final String TAG_TIMES ="TIMES";
    private TextView mTextViewResult;

    public  String  idc;
    ArrayList<HashMap<String, String>> personList;
    ListView mListViewList;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        mTextViewResult = (TextView) findViewById(R.id.textView_main_result);
        mListViewList = (ListView) findViewById(R.id.listView_main_list);
        Intent intent=getIntent();
        String DataM1=intent.getStringExtra("data1");
        String TimeM1=intent.getStringExtra("time1");
        String DataM2=intent.getStringExtra("data2");
        String TimeM2=intent.getStringExtra("time2");
        String  AREA = intent.getStringExtra("area");
        String  ID = intent.getStringExtra("ID");
        String  GENDER = intent.getStringExtra("GENDER");
        idc = intent.getStringExtra("id");
        System.out.println(idc);
        GetData task = new GetData();
        System.out.println(AREA +"|"+ DataM1+"|"+TimeM1+"|"+DataM2+"|"+TimeM2);    //test 용
        task.execute( AREA,DataM1,TimeM1,DataM2,TimeM2,ID,GENDER);
        personList = new ArrayList<HashMap<String, String>>();
        mListViewList.setOnItemClickListener(itemClickListenerOfLanguageList);

    }
    private OnItemClickListener itemClickListenerOfLanguageList = new OnItemClickListener()
    {

        public void onItemClick(AdapterView<?> adapterView,View view, int pos, long id)
        {
            mListViewList = (ListView) findViewById(R.id.listView_main_list);
            Object vo = (Object)adapterView.getAdapter().getItem(pos);  //리스트뷰의 포지션 내용을 가져옴.
            Intent intent=new Intent(getApplicationContext(),therdlay.class);
            intent.putExtra("text",vo.toString());
            intent.putExtra("id",idc);
            System.out.println(idc);
            startActivity(intent);
            //vo 를 변수삼아 게시판창띄우면됨
        }
    };
    private class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(pro.this,
                    "Please Wait", null, true, true);
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Log.d(TAG, "response-"+result);
            if (result.contains("{\"Result\":[")){
                mTextViewResult.setText(result);
                mJsonString = result;
                showResult();
            }
            else {
                mTextViewResult.setText(errorString);
            }

        } //php 결과값 성공 실패 여부 판단
        @Override
        protected String doInBackground(String... params) {
            String GENDER = params[6];
            String ID = params[5];
            String AREA = params[0];
            String DataM1 = params[1];
            String TimeM1 = params[2];
            String DataM2 = params[3];
            String TimeM2 = params[4];
            String serverURL = "http://118.34.34.178/search.php";
            String postParameters = ("GENDER="+GENDER+"&AREA="+AREA+"&DATA1="+DataM1+"&TIME1="+TimeM1+"&DATA2="+DataM2+"&TIME2="+TimeM2+"&ID="+ID);
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
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);
                String NO = item.getString(TAG_NO);
                String ID = item.getString(TAG_ID);
                String AREA= item.getString(TAG_AREA);
                String TITLE =item.getString(TAG_TITLE);
                String CONTENTS = item.getString(TAG_CONTENTS);
                String GENDER = item.getString(TAG_GENDER);
                String TIMES = item.getString(TAG_TIMES);
                HashMap<String, String> persons = new HashMap<String, String>();
                persons.put(TAG_NO, NO);
                persons.put(TAG_ID, ID);
                persons.put(TAG_AREA, AREA);
                persons.put(TAG_TITLE, TITLE);
                persons.put(TAG_CONTENTS, CONTENTS);
                persons.put(TAG_GENDER, GENDER);
                persons.put(TAG_TIMES, TIMES);
                personList.add(persons);
            }
        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
        ListAdapter adapter = new SimpleAdapter(
                pro.this, personList, R.layout.item_list,
                new String[]{TAG_ID,TAG_NO,TAG_AREA,TAG_GENDER,TAG_TITLE,TAG_CONTENTS,TAG_TIMES},
                new int[]{R.id.id, R.id.NO,R.id.AREA,R.id.GENDER,R.id.TITLE,R.id.CONTENTS,R.id.TIME}
        );
        mListViewList.setAdapter(adapter);

    }
}
