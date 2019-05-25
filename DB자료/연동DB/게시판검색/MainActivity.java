package com.example.msi.myapplication;

import android.app.ProgressDialog;
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


public class MainActivity extends AppCompatActivity {
    private static String TAG = "phpquerytest";
    private static final String TAG_JSON="Result";
    private static final String TAG_ID = "ID";
    private static final String TAG_NO = "NO";
    private static final String TAG_AREA = "AREA";
    private static final String TAG_GENDER = "GENDER";
    private static final String TAG_TITLE = "TITLE";
    private static final String TAG_CONTENTS = "CONTENTS";
    private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> personList;
    ListView mListViewList;
    EditText mEditTextSearchKeyword1,mEditTextSearchKeyword2;
    String mJsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        mListViewList = (ListView) findViewById(R.id.listView_main_list);
        mEditTextSearchKeyword1 = (EditText) findViewById(R.id.editText_main_searchKeyword1);
        mEditTextSearchKeyword2 = (EditText) findViewById(R.id.editText_main_searchKeyword2);
        Button button_search = (Button) findViewById(R.id.button_main_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                personList.clear();
                GetData task = new GetData();
                task.execute( mEditTextSearchKeyword1.getText().toString(), mEditTextSearchKeyword2.getText().toString() );
            }
        });
        personList = new ArrayList<HashMap<String, String>>();
    }


    private class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this,
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

        }
        @Override
        protected String doInBackground(String... params) {
            String searchKeyword1 = params[0];
            String searchKeyword2 = params[1];
            String serverURL = "http://118.34.34.178/search.php";
            String postParameters = "GENDER=" + searchKeyword1 +"&AREA=" + searchKeyword2  ;
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
            System.out.println("1");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);
                String NO = item.getString(TAG_NO);
                String ID = item.getString(TAG_ID);
                String AREA= item.getString(TAG_AREA);
                String TITLE =item.getString(TAG_TITLE);
                String CONTENTS = item.getString(TAG_CONTENTS);
                String GENDER = item.getString(TAG_GENDER);

                //mTextViewResult.setText(ID);
                HashMap<String, String> persons = new HashMap<String, String>();
                persons.put(TAG_NO, NO);
                persons.put(TAG_ID, ID);
                persons.put(TAG_AREA, AREA);
                persons.put(TAG_TITLE, TITLE);
                persons.put(TAG_CONTENTS, CONTENTS);
                persons.put(TAG_GENDER, GENDER);
                personList.add(persons);
            }
        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, personList, R.layout.itme_list,
                new String[]{TAG_ID,TAG_NO,TAG_AREA,TAG_GENDER,TAG_TITLE,TAG_CONTENTS},
                new int[]{R.id.id, R.id.NO,R.id.AREA,R.id.GENDER,R.id.TITLE,R.id.CONTENTS}
        );
        mListViewList.setAdapter(adapter);

    }

}
