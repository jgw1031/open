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

public class Write extends AppCompatActivity {
    private static String IP_ADDRESS = "192.168.0.72";
    private static String TAG = "Write";
    private EditText mEditTextTITLE;
    private EditText mEditTextCONTENTS;
    private EditText mEditTextAREA;
    private EditText mEditTextTIME;
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
                String TITLE = mEditTextTITLE.getText().toString();
                String CONTENTS = mEditTextCONTENTS.getText().toString();
                String AREA = mEditTextAREA.getText().toString();
                String TIME = mEditTextTIME.getText().toString();
                Write.InsertData task = new Write.InsertData();
                task.execute("http://" + IP_ADDRESS + "/write.php",ID,TITLE,CONTENTS,AREA,TIME);
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



    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Write.this,
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
            String TITLE = (String)params[2];
            String CONTENTS = (String)params[3];
            String AREA = (String)params[4];
            String TIMES = (String)params[5];
            String serverURL = (String)params[0];
            String postParameters = "TITLE=" + TITLE +"&CONTENTS=" + CONTENTS+"&ID=" + ID+"&AREA="+AREA+"&TIMES="+TIMES;
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

