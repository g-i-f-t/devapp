package com.example.activity_maim.login;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.activity_maim.HashSerivce;
import com.example.activity_maim.R;
import com.example.activity_maim.activity_main;
import com.example.activity_maim.signup.SignUpActivity;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;


public class activity_login extends AppCompatActivity {

    private TextView signup;
    private Button btn;
    private registDB loginTask;
    TextInputEditText et_id, et_pw;
    String sld, sPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = (TextInputEditText)findViewById(R.id.et_id);
        et_pw = (TextInputEditText)findViewById(R.id.et_pw);

        bt_Join();
//        signup=findViewById(R.id.signup);
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent registerIntent = new Intent(activity_login.this, activity_register.class);
//                activity_login.this.startActivity(registerIntent);
//
//            }
//        });

        btn=findViewById(R.id.bt_Join);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent loginIntent = new Intent(activity_login.this, activity_main.class);
//                activity_login.this.startActivity(loginIntent);
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity_login.this);

                sld = et_id.getText().toString();
                sPw = et_pw.getText().toString();

                JSONObject login = new JSONObject();
                try {
                    login.put("email", sld);
                    login.put("password", HashSerivce.sha256(sPw));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                registDB rdb = new registDB();
                JSONObject result = null;

                try {
                     result = rdb.execute(login.toString()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Log.e("RECV DATA", Integer.toString(result.getInt("code")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    if(result.getInt("code") == 200){
                        Log.e("RESULT","로그인성공");
                        Intent loginIntent = new Intent(activity_login.this, activity_main.class);
                activity_login.this.startActivity(loginIntent);
                    }
                    else{
                        Log.e("result",result.toString());
                        Log.e("RESULT","에러"+"CODE");
                        dialog
                                .setTitle("알림")
                                .setMessage("등록중 에러가 발생했습니다! errcode : "+ "code")
                                .setCancelable(true);
//                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//
//                                    }
//                                });
                        AlertDialog show = dialog.show();
//                        Intent loginfIntent = new Intent(activity_login.this, activity_login.class);
//                        activity_login.this.startActivity(loginfIntent);



//                        출처: https://cholol.tistory.com/404?category=572900 [IT, I Think ]


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void bt_Join(){
        TextView join=findViewById(R.id.signup);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_login.this, SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }



    public class registDB extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String ... unused) {

            System.out.println("start");
            /* 인풋 파라메터값 생성 */
            JSONObject result = null;
            try {
                /* 서버연결 */
                URL url = new URL("http://117.17.102.139:8080/developer/auth");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/json");
                conn.setRequestProperty("Charset","UTF-8");
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);


                /* 안드로이드 -> 서버 파라메터값 전달 */
                Log.i("saveGame", unused[0]);
                OutputStream outs = conn.getOutputStream();
                outs.write(unused[0].getBytes("UTF-8"));
                outs.flush();
                outs.close();
                System.out.println(conn.getResponseCode());
                /* 서버 -> 안드로이드 파라메터값 전달 */
                String data = "";

                InputStream is = conn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String line = null;
                StringBuffer buff = new StringBuffer();
                while ( ( line = in.readLine() ) != null )
                {
                    buff.append(line + "\n");
                }

                Log.i("saveGame", buff.toString());
                result = new JSONObject(buff.toString().trim()) ;
                System.out.println("RECV DATA" + data);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

    }}




