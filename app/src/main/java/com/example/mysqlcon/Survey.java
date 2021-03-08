package com.example.mysqlcon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Survey extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG = "phptest";
    private TextView mTextViewResult;
    Integer poor, water_tank_num, water_tank_clean, water_tank_time, straw_fead_tank, straw_normal, straw_resting_place, outward_hygiene, shade,
            summer_ventilating, mist_spray, wind_block, winter_ventilating, winter_straw, calf_warm, calf_wind_block, limp, hair_loss, cough, runny_nose,
            ophthalmic_secretion, respiratory_failure, diarrhea, ruminant, fall_dead, horn, horn_anesthesia, horn_painkiller, castration,
            castration_anesthesia, castration_painkiller, struggle, harmony, touch_possibility, touch_near, touch_far, touch_impossibility, farm;
    Button sndbtn, backbtn;
    String rslt, name, tel, age, num, sam;


    Integer protocol1, protocol2, protocol3, protocol4, total;
    Integer protocol1_length, protocol2_length, protocol3_length, protocol4_length;
    JSONArray protocol1_info, protocol2_info, protocol3_info, protocol4_info;
    JSONArray temp = new JSONArray();

    JSONObject data = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Intent intent = getIntent();

        sndbtn = (Button) findViewById(R.id.sndbtn);
        backbtn = (Button) findViewById(R.id.backbtn);

        final EditText ed_1_poor = (EditText) findViewById(R.id.a1);
        final EditText ed_8_outward_hygiene = (EditText) findViewById(R.id.a8);
        final EditText ed_17_limp = (EditText) findViewById(R.id.a17);
        final EditText ed_18_hair_loss = (EditText) findViewById(R.id.a18);
        final EditText ed_19_cough = (EditText) findViewById(R.id.a19);
        final EditText ed_20_runny_nose = (EditText) findViewById(R.id.a20);
        final EditText ed_21_ophthalmic_secretion = (EditText) findViewById(R.id.a21);
        final EditText ed_22_respiratory_failure = (EditText) findViewById(R.id.a22);
        final EditText ed_23_diarrhea = (EditText) findViewById(R.id.a23);
        final EditText ed_24_ruminant = (EditText) findViewById(R.id.a24);
        final EditText ed_25_fall_dead = (EditText) findViewById(R.id.a25);
        final EditText ed_32_struggle = (EditText) findViewById(R.id.a32);
        final EditText ed_33_harmony = (EditText) findViewById(R.id.a33);
        final EditText ed_34_touch_possibility = (EditText) findViewById(R.id.a34);
        final EditText ed_35_touch_near = (EditText) findViewById(R.id.a35);
        final EditText ed_36_touch_far = (EditText) findViewById(R.id.a36);
        final EditText ed_37_touch_impossibility = (EditText) findViewById(R.id.a37);

        RadioGroup rdiog_2_water_tank_num = (RadioGroup) findViewById(R.id.rdogrp2);

        rdiog_2_water_tank_num.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a2_1) {
                    water_tank_num = 1;
                } else if (checkedId == R.id.a2_2) {
                    water_tank_num = 2;
                }
            }
        });

        RadioGroup rdiog_3_water_tank_clean = (RadioGroup) findViewById(R.id.rdogrp3);

        rdiog_3_water_tank_clean.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a3_1) {
                    water_tank_clean = 1;
                } else if (checkedId == R.id.a3_2) {
                    water_tank_clean = 2;
                } else if (checkedId == R.id.a3_3) {
                    water_tank_clean = 3;
                }
            }
        });

        RadioGroup rdiog_4_water_tank_time = (RadioGroup) findViewById(R.id.rdogrp4);

        rdiog_4_water_tank_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a4_1) {
                    water_tank_time = 1;
                } else if (checkedId == R.id.a4_2) {
                    water_tank_time = 2;
                } else if (checkedId == R.id.a4_3) {
                    water_tank_time = 3;
                }
            }
        });

        RadioGroup rdiog_5_straw_fead_tank = (RadioGroup) findViewById(R.id.rdogrp5);

        rdiog_5_straw_fead_tank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a5_1) {
                    straw_fead_tank = 1;
                } else if (checkedId == R.id.a5_2) {
                    straw_fead_tank = 2;
                } else if (checkedId == R.id.a5_3) {
                    straw_fead_tank = 3;
                } else if (checkedId == R.id.a5_4) {
                    straw_fead_tank = 4;
                }
            }
        });

        RadioGroup rdiog_6_straw_normal = (RadioGroup) findViewById(R.id.rdogrp6);

        rdiog_6_straw_normal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a6_1) {
                    straw_normal = 1;
                } else if (checkedId == R.id.a6_2) {
                    straw_normal = 2;
                } else if (checkedId == R.id.a6_3) {
                    straw_normal = 3;
                } else if (checkedId == R.id.a6_4) {
                    straw_normal = 4;
                }
            }
        });

        RadioGroup rdiog_7_straw_resting_place = (RadioGroup) findViewById(R.id.rdogrp7);

        rdiog_7_straw_resting_place.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a7_1) {
                    straw_resting_place = 1;
                } else if (checkedId == R.id.a7_2) {
                    straw_resting_place = 2;
                } else if (checkedId == R.id.a7_3) {
                    straw_resting_place = 3;
                } else if (checkedId == R.id.a7_4) {
                    straw_resting_place = 4;
                }
            }
        });

        RadioGroup rdiog_9_shade = (RadioGroup) findViewById(R.id.rdogrp9);

        rdiog_9_shade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a9_1) {
                    shade = 1;
                } else if (checkedId == R.id.a9_2) {
                    shade = 2;
                }
            }
        });

        RadioGroup rdiog_10_summer_ventilating = (RadioGroup) findViewById(R.id.rdogrp10);

        rdiog_10_summer_ventilating.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a10_1) {
                    summer_ventilating = 1;
                } else if (checkedId == R.id.a10_2) {
                    summer_ventilating = 2;
                }
            }
        });

        RadioGroup rdiog_11_mist_spray = (RadioGroup) findViewById(R.id.rdogrp11);

        rdiog_11_mist_spray.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a11_1) {
                    mist_spray = 1;
                } else if (checkedId == R.id.a11_2) {
                    mist_spray = 2;
                }
            }
        });

        RadioGroup rdiog_12_wind_block = (RadioGroup) findViewById(R.id.rdogrp12);

        rdiog_12_wind_block.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a12_1) {
                    wind_block = 1;
                } else if (checkedId == R.id.a12_2) {
                    wind_block = 2;
                }
            }
        });

        RadioGroup rdiog_13_winter_ventilating = (RadioGroup) findViewById(R.id.rdogrp13);

        rdiog_13_winter_ventilating.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a13_1) {
                    winter_ventilating = 1;
                } else if (checkedId == R.id.a13_2) {
                    winter_ventilating = 2;
                }
            }
        });

        RadioGroup rdiog_14_winter_straw = (RadioGroup) findViewById(R.id.rdogrp14);

        rdiog_14_winter_straw.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a14_1) {
                    winter_straw = 1;
                } else if (checkedId == R.id.a14_2) {
                    winter_straw = 2;
                }
            }
        });

        RadioGroup rdiog_15_calf_warm = (RadioGroup) findViewById(R.id.rdogrp15);

        rdiog_15_calf_warm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a15_1) {
                    calf_warm = 1;
                } else if (checkedId == R.id.a15_2) {
                    calf_warm = 2;
                }
            }
        });

        RadioGroup rdiog_16_calf_wind_block = (RadioGroup) findViewById(R.id.rdogrp16);

        rdiog_16_calf_wind_block.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a16_1) {
                    calf_wind_block = 1;
                } else if (checkedId == R.id.a16_2) {
                    calf_wind_block = 2;
                }
            }
        });

        RadioGroup rdiog_26_horn = (RadioGroup) findViewById(R.id.rdogrp26);

        rdiog_26_horn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a26_1) {
                    horn = 1;
                } else if (checkedId == R.id.a26_2) {
                    horn = 2;
                } else if (checkedId == R.id.a26_3) {
                    horn = 3;
                } else if (checkedId == R.id.a26_4) {
                    horn = 4;
                }
            }
        });

        RadioGroup rdiog_27_horn_anesthesia = (RadioGroup) findViewById(R.id.rdogrp27);

        rdiog_27_horn_anesthesia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a27_1) {
                    horn_anesthesia = 1;
                } else if (checkedId == R.id.a27_2) {
                    horn_anesthesia = 2;
                }
            }
        });

        RadioGroup rdiog_28_horn_painkiller = (RadioGroup) findViewById(R.id.rdogrp28);

        rdiog_28_horn_painkiller.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a28_1) {
                    horn_painkiller = 1;
                } else if (checkedId == R.id.a28_2) {
                    horn_painkiller = 2;
                }
            }
        });

        RadioGroup rdiog_29_castration = (RadioGroup) findViewById(R.id.rdogrp29);

        rdiog_29_castration.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a29_1) {
                    castration = 1;
                } else if (checkedId == R.id.a29_2) {
                    castration = 2;
                } else if (checkedId == R.id.a29_3) {
                    castration = 3;
                } else if (checkedId == R.id.a29_4) {
                    castration = 4;
                }
            }
        });

        RadioGroup rdiog_30_castration_anesthesia = (RadioGroup) findViewById(R.id.rdogrp30);

        rdiog_30_castration_anesthesia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a30_1) {
                    castration_anesthesia = 1;
                } else if (checkedId == R.id.a30_2) {
                    castration_anesthesia = 2;
                }
            }
        });

        RadioGroup rdiog_31_castration_painkiller = (RadioGroup) findViewById(R.id.rdogrp31);

        rdiog_31_castration_painkiller.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.a31_1) {
                    castration_painkiller = 1;
                } else if (checkedId == R.id.a31_2) {
                    castration_painkiller = 2;
                }
            }
        });


        sndbtn.setOnClickListener(new View.OnClickListener() {
            //버튼 클릭시 이벤트입니다.
            @Override
            public void onClick(View view) {
                String poor = ed_1_poor.getText().toString();
                String outward_hygiene = ed_8_outward_hygiene.getText().toString();
                String limp = ed_17_limp.getText().toString();
                String hair_loss = ed_18_hair_loss.getText().toString();
                String cough = ed_19_cough.getText().toString();
                String runny_nose = ed_20_runny_nose.getText().toString();
                String ophthalmic_secretion = ed_21_ophthalmic_secretion.getText().toString();
                String respiratory_failure = ed_22_respiratory_failure.getText().toString();
                String diarrhea = ed_23_diarrhea.getText().toString();
                String ruminant = ed_24_ruminant.getText().toString();
                String fall_dead = ed_25_fall_dead.getText().toString();
                String struggle = ed_32_struggle.getText().toString();
                String harmony = ed_33_harmony.getText().toString();
                String touch_possibility = ed_34_touch_possibility.getText().toString();
                String touch_near = ed_35_touch_near.getText().toString();
                String touch_far = ed_36_touch_far.getText().toString();
                String touch_impossibility = ed_37_touch_impossibility.getText().toString();

                String water_tank_num_1 = Integer.toString(water_tank_num);
                String water_tank_clean_1 = Integer.toString(water_tank_clean);
                String water_tank_time_1 = Integer.toString(water_tank_time);
                String straw_fead_tank_1 = Integer.toString(straw_fead_tank);
                String straw_normal_1 = Integer.toString(straw_normal);
                String straw_resting_place_1 = Integer.toString(straw_resting_place);
                String shade_1 = Integer.toString(shade);
                String winter_ventilating_1 = Integer.toString(winter_ventilating);
                String summer_ventilating_1 = Integer.toString(summer_ventilating);
                String mist_spray_1 = Integer.toString(mist_spray);
                String wind_block_1 = Integer.toString(wind_block);
                String winter_straw_1 = Integer.toString(winter_straw);
                String calf_warm_1 = Integer.toString(calf_warm);
                String calf_wind_block_1 = Integer.toString(calf_wind_block);
                String horn_1 = Integer.toString(horn);
                String horn_anesthesia_1 = Integer.toString(horn_anesthesia);
                String horn_painkiller_1 = Integer.toString(horn_painkiller);
                String castration_1 = Integer.toString(castration);
                String castration_anesthesia_1 = Integer.toString(castration_anesthesia);
                String castration_painkiller_1 = Integer.toString(castration_painkiller);

                Survey.InsertData task = new Survey.InsertData();
                task.execute("http://" + IP_ADDRESS + "/survey.php", poor, water_tank_num_1, water_tank_clean_1, water_tank_time_1, straw_fead_tank_1, straw_normal_1, straw_resting_place_1, outward_hygiene, shade_1, summer_ventilating_1,
                         mist_spray_1, wind_block_1, winter_ventilating_1, winter_straw_1, calf_warm_1, calf_wind_block_1, limp, hair_loss, cough, runny_nose, ophthalmic_secretion, respiratory_failure, diarrhea, ruminant, fall_dead,
                         horn_1, horn_anesthesia_1, horn_painkiller_1, castration_1, castration_anesthesia_1, castration_painkiller_1, struggle, harmony, touch_possibility, touch_near, touch_far, touch_impossibility);

            }
        });
        backbtn.setOnClickListener(new View.OnClickListener()
        {
            //버튼 클릭시 이벤트입니다.
            @Override
            public void onClick (View view){
                finish();
            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Survey.this,
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

            String poor = (String) params[1];
            String water_tank_num = (String) params[2];
            String water_tank_clean = (String) params[3];
            String water_tank_time = (String) params[4];
            String straw_fead_tank = (String) params[5];
            String straw_normal = (String) params[6];
            String straw_resting_place = (String) params[7];
            String outward_hygiene = (String) params[8];
            String shade = (String) params[9];
            String summer_ventilating = (String) params[10];
            String mist_spray = (String) params[11];
            String wind_block = (String) params[12];
            String winter_ventilating = (String) params[13];
            String winter_straw = (String) params[14];
            String calf_warm = (String) params[15];
            String calf_wind_block = (String) params[16];
            String limp = (String) params[17];
            String hair_loss = (String) params[18];
            String cough = (String) params[19];
            String runny_nose = (String) params[20];
            String ophthalmic_secretion = (String) params[21];
            String respiratory_failure = (String) params[22];
            String diarrhea = (String) params[23];
            String ruminant = (String) params[24];
            String fall_dead = (String) params[25];
            String horn = (String) params[26];
            String horn_anesthesia = (String) params[27];
            String horn_painkiller = (String) params[28];
            String castration = (String) params[29];
            String castration_anesthesia = (String) params[30];
            String castration_painkiller = (String) params[31];
            String struggle = (String) params[32];
            String harmony = (String) params[33];
            String touch_possibility = (String) params[34];
            String touch_near = (String) params[35];
            String touch_far = (String) params[36];
            String touch_impossibility = (String) params[37];

            String serverURL = (String) params[0];
            String postParameters = "poor=" + poor + "&water_tank_num=" + water_tank_num + "&water_tank_clean=" + water_tank_clean + "&water_tank_time=" + water_tank_time + "&straw_fead_tank=" + straw_fead_tank +
                    "&straw_normal=" + straw_normal + "&straw_resting_place=" + straw_resting_place + "&outward_hygiene=" + outward_hygiene + "&shade=" + shade + "&summer_ventilating=" + summer_ventilating +
                    "&mist_spray=" + mist_spray + "&wind_block=" + wind_block + "&winter_ventilating=" + winter_ventilating + "&winter_straw=" + winter_straw + "&calf_warm=" + calf_warm + "&calf_wind_block=" + calf_wind_block +
                    "&limp=" + limp + "&hair_loss=" + hair_loss + "&cough=" + cough + "&runny_nose=" + runny_nose + "&ophthalmic_secretion=" + ophthalmic_secretion + "&respiratory_failure=" + respiratory_failure +
                    "&diarrhea=" + diarrhea + "&ruminant=" + ruminant + "&fall_dead=" + fall_dead + "&horn=" + horn + "&horn_anesthesia=" + horn_anesthesia + "&horn_painkiller=" + horn_painkiller +
                    "&castration=" + castration + "&castration_anesthesia=" + castration_anesthesia + "&castration_painkiller=" + castration_painkiller + "&struggle=" + struggle + "&harmony=" + harmony +
                    "&touch_possibility=" + touch_possibility + "&touch_near=" + touch_near + "&touch_far=" + touch_far + "&touch_impossibility=" + touch_impossibility;


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
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
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
