package com.example.mysqlcon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG = "phptest";
    Integer iage, ifarm;
    private EditText et_1_name;
    private EditText et_2_tel;
    private EditText et_3_age;
    private EditText et_4_num;
    private EditText et_5_sam;
    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_1_name = (EditText)findViewById(R.id.namereg);
        et_2_tel = (EditText)findViewById(R.id.telreg);
        et_3_age = (EditText)findViewById(R.id.agereg);
        et_4_num = (EditText)findViewById(R.id.numberreg);
        et_5_sam = (EditText)findViewById(R.id.samplereg);

        RadioGroup rgfarm = (RadioGroup)findViewById(R.id.farmgrp);

        rgfarm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.farm1){
                    ifarm = 1;
                } else if (checkedId == R.id.farm2){
                    ifarm = 2;
                } else if (checkedId == R.id.farm3){
                    ifarm = 3;
                }
            }
        });

        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());


        Button buttonInsert = (Button)findViewById(R.id.regbtn);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_1_name.getText().toString();
                String tel = et_2_tel.getText().toString();
                String age = et_3_age.getText().toString();
                String num = et_4_num.getText().toString();
                String sam = et_5_sam.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insert.php", name, tel, age, num, sam);


                et_1_name.setText("");
                et_2_tel.setText("");
                et_3_age.setText("");
                et_4_num.setText("");
                et_5_sam.setText("");

                Intent intent = new Intent(MainActivity.this, Survey.class);
                startActivity(intent);
                
            }
        });

    }



    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

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
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String name = (String)params[1];
            String tel = (String)params[2];
            String age = (String)params[3];
            String num = (String)params[4];
            String sam = (String)params[5];

            String serverURL = (String)params[0];
            String postParameters = "name=" + name + "&tel=" + tel + "&age=" + age + "&num=" + num + "&sam=" + sam;


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