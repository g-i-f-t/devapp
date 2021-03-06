package com.example.activity_maim;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.example.activity_maim.splash.ProfileManager;

import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class AddGames extends AppCompatActivity {
    private ProfileManager profileManager;
    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    private String names,contents;
    private String category;
    private EditText goalPrice,inveinfo,invecond;
    private Button save;
    private JSONObject obj;
    private int count;
    private  Button btn2;
    private ImageView[] views;
    EditText content,name;
    JSONObject gamevo;
    JSONArray arr;

    ArrayList<String> list;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_games);
        profileManager = new ProfileManager();

        Button btn2=(Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/hwyWvekLPSUDSU8o9"));
                startActivity(intent1);
            }
        });
        count=0;
        views=new ImageView[6];
        views[0]=findViewById(R.id.SimpleView0);
        views[1]=findViewById(R.id.SimpleView1);
        views[2]=findViewById(R.id.SimpleView2);
        views[3]=findViewById(R.id.SimpleView3);
        initView();
        initSpinner();
        ClickSave();
        initImage();
    }
    public void onclick(){
        Intent btnIntent = new Intent(Intent.ACTION_PICK);
        btnIntent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(btnIntent, GET_GALLERY_IMAGE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            views[count].setVisibility(View.VISIBLE);
            Uri selectedImageUri = data.getData();
            Glide.with(this)
                    .load(selectedImageUri)
                    .into(views[count]);
            count++;
            obj= new JSONObject();
            arr=new JSONArray();
            JSONObject a=new JSONObject();
            try {
                a.put("describeImage",selectedImageUri.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            arr.put(a);

        }
    }


    private void initImage(){
        Button btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>3){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(AddGames.this);
                        dialog
                                .setTitle("알림")
                                .setMessage("최대 4개의 사진만 저장 가능합니다.")
                                .setCancelable(true);

                        AlertDialog show = dialog.show();
                }else{
                    onclick();
                }
            }
        });
    }


    private void initView(){
        save=findViewById(R.id.savefund);
        goalPrice=findViewById(R.id.editText);
        invecond=findViewById(R.id.invecond);
        inveinfo=findViewById(R.id.inveinfo);
        name=findViewById(R.id.contentname);
        content = (EditText) findViewById(R.id.content);
    }

    private void initData(){
        String userSeqId = profileManager.getLoginKey(this).get("userSeqNo");
        gamevo=new JSONObject();
        try {
            obj.put("category",category);
            obj.put("companyIntroduction","무엇을 넣나요");
            obj.put("currentPrice","0");

            obj.put("gameInformation",content.getText().toString());
            obj.put("goalPrice",Long.valueOf(goalPrice.getText().toString()));
            obj.put("investmentCondition",invecond.getText().toString());
            obj.put("investmentInformation",inveinfo.getText().toString());
            obj.put("name",name.getText().toString());
            obj.put("profileImage","http://117.17.102.139:8080/default_images.jpg");
            obj.put("success",false);

            gamevo.put("game",obj);
            gamevo.put("userSeqId",userSeqId);
            gamevo.put("gameDescribeImages",arr);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initSpinner(){
        Spinner s = (Spinner) findViewById(R.id.spinner);
        Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    }
        });
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void ClickSave(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
                String param=gamevo.toString();
                saveGame saveGame=new saveGame();
                JSONObject result=null;
                try {
                    result=saveGame.execute(param).get();

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

                AlertDialog.Builder dialog=new AlertDialog.Builder(AddGames.this);
                try {
                    if(result.getInt("code") == 200){
                        Log.e("RESULT","저장성공");
                        Intent intent=new Intent(AddGames.this,fundlist.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
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
    public class saveGame extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String ... unused) {
            System.out.println("start");
            /* 인풋 파라메터값 생성 */
            JSONObject result = null;
            try {
                /* 서버연결 */
                URL url = new URL("http://117.17.102.139:8080/game/insert");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/json");
                conn.setRequestProperty("Charset","UTF-8");
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);


                /* 안드로이드 -> 서버 파라메터값 전달 */
                Log.i("registDB", unused[0]);
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

                Log.i("registDBresult" , buff.toString());
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

    }





}

