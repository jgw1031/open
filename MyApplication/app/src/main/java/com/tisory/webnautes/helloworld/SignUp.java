package com.tisory.webnautes.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUp extends AppCompatActivity {
        private static String IP_ADDRESS = "118.34.34.178";
        private static String TAG = "phptest1";
        private EditText mEditTextID;
        private EditText mEditTextNAME;
        private EditText mEditTextPASSWORD;
        private EditText mEditTextAGE;
        private EditText mEditTextPHONE;
        private EditText mEditTextGENDER;
        private TextView mTextViewResult;


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
            mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
            mTextViewResult.setMovementMethod(new ScrollingMovementMethod());
            Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
            buttonInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ID = mEditTextID.getText().toString();
                    String NAME = mEditTextNAME.getText().toString();
                    String PASSWORD = mEditTextPASSWORD.getText().toString();
                    String AGE = mEditTextAGE.getText().toString();
                    String PHONE = mEditTextPHONE.getText().toString();
                    String GENDER = mEditTextGENDER.getText().toString();
                    InsertData task = new InsertData();
                    task.execute("http://" + IP_ADDRESS + "/insert1.php", ID,NAME,PASSWORD,AGE,PHONE,GENDER);
                    mEditTextID.setText("");
                    mEditTextNAME.setText("");
                    mEditTextPASSWORD.setText("");
                    mEditTextAGE.setText("");
                    mEditTextPHONE.setText("");
                    mEditTextGENDER.setText("");
                    if(R.id.button_main_insert == v.getId()){
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignUp.this);
                        alertDialogBuilder.setTitle("회원가입 성공");
                        alertDialogBuilder.setMessage("회원가입이 되었습니다.");
                        alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(
                                        getApplicationContext(),
                                        MainActivity.class
                                );
                                startActivity(intent);
                                finish();
                            }
                        });
                        alertDialogBuilder.show();
                    }
                }
            });

        }
class InsertData extends AsyncTask<String, Void, String>{
    ProgressDialog progressDialog;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(SignUp.this,
                "Please Wait", null, true, true);
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        mTextViewResult.setText(result);
        Log.d(TAG, "POST response  - " + result);
    }
    @Override
    protected String doInBackground(String... params) {
        String ID = (String)params[1];
        String NAME = (String)params[2];
        String PASSWORD = (String)params[3];
        String AGE = (String)params[4];
        String PHONE = (String)params[5];
        String GENDER = (String)params[6];
        String serverURL = (String)params[0];
        String postParameters = "ID=" + ID + "&NAME=" + NAME +"&PASSWORD=" + PASSWORD + "&AGE=" + AGE +"&PHONE=" + PHONE +"&GENDER="+ GENDER;
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

