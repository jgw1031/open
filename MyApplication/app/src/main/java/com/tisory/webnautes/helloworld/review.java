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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class review extends AppCompatActivity {
    private static String IP_ADDRESS = "118.34.34.178";
    private static String TAG = "Write";
    private EditText mEditTextTEXT;
    private EditText mEditTextSTAR;
    private EditText mEditTextAREA;
    private EditText mEditTextTIME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        mEditTextTEXT = (EditText)findViewById(R.id.review_text2);
        mEditTextSTAR = (EditText)findViewById(R.id.review_text);
        mEditTextAREA = (EditText)findViewById(R.id.review_text3);
        mEditTextTIME = (EditText)findViewById(R.id.review_text4);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button3 = (Button)findViewById(R.id.button3);
        Intent con=getIntent();
        final String id =con.getStringExtra("id");
        final String ID =con.getStringExtra("ID");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(review.this);
                alertDialogBuilder.setTitle("리뷰 등록");
                alertDialogBuilder.setMessage("정말 취소하시겠습니까?");
                alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
                String TEXT = mEditTextTEXT.getText().toString();
                String STAR = mEditTextSTAR.getText().toString();
                String AREA = mEditTextAREA.getText().toString();
                String TIME = mEditTextTIME.getText().toString();
                review.InsertData task = new review.InsertData();
                task.execute("http://" + IP_ADDRESS + "/review.php",ID,id,TEXT,STAR,AREA,TIME);
                mEditTextTEXT.setText("");
                mEditTextSTAR.setText("");
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(review.this);
                alertDialogBuilder.setTitle("리뷰 등록");
                alertDialogBuilder.setMessage("리뷰등록이 완료되었습니다.");
                alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }

                });
                alertDialogBuilder.show();
            }
        });

    }



    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(review.this,
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
            String id = (String)params[2];
            String TEXT = (String)params[3];
            String STAR = (String)params[4];
            String AREA = (String)params[5];
            String TIMES = (String)params[6];
            String serverURL = (String)params[0];
            String postParameters = "ID=" + id +"&TARGETID="+ID +"&TEXT="+TEXT+"&STAR="+STAR+"&AREA="+AREA+"&TIMES="+TIMES;
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
}

