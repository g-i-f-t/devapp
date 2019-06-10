package com.example.activity_maim.signup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity_maim.VO.AuthVO;
import com.example.activity_maim.R;
import com.example.activity_maim.Room.AppDatabase;
import com.example.activity_maim.Room.UserDao;
import com.example.activity_maim.User;
import com.example.activity_maim.activity_main;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class Login_API2 extends AppCompatActivity {
    private TextView tv_outPut;

    //form에서 입력했던 사용자 정보들을 Room에 저장하고 서버에 POST로 보내기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__api2);
        // 위젯에 대한 참조.
        tv_outPut = (TextView) findViewById(R.id.tv_outPut);

        findViewById(R.id.nextBtn).setOnClickListener(goMain);

        Intent prevIntent = getIntent();
        final HashMap<String, String> extra = (HashMap<String, String>) prevIntent.getSerializableExtra("data");

        //URL 설정
        String url = "http://117.17.102.139:8080/developer";
        JsonObject params = new JsonObject();
        params.addProperty("password", extra.get("password"));
        params.addProperty("name", extra.get("name"));
        params.addProperty("email", extra.get("email"));
        params.addProperty("authCode", extra.get("code"));
        params.addProperty("scope", extra.get("scope")); //해당 계좌 접근 권한

        NetWorkTask netWorkTask = null;
        netWorkTask = new NetWorkTask(url, new Gson().toJson(params));

        TextView authCode = findViewById(R.id.code);
        TextView scope = findViewById(R.id.scope);
        TextView clientInfo = findViewById(R.id.client_info);
        TextView email = findViewById(R.id.email);
        TextView password = findViewById(R.id.password);
        TextView name = findViewById(R.id.name);

        authCode.setText(extra.get("code"));
        scope.setText(extra.get("scope"));
        clientInfo.setText(extra.get("client_info"));
        email.setText(extra.get("email"));
        password.setText(extra.get("password"));
        name.setText(extra.get("name"));

        try {
            AuthVO result = netWorkTask.execute().get();
            if(result != null) {
                tv_outPut.setText(result.getAccess_token() != null ? result.getAccess_token() : "" + ", " + result.getUser_seq_no() != null ? result.getUser_seq_no() : "" );

                if(result.getCode() == 401)
                    tv_outPut.setText("이미 가입했었는데요?");
                if(result.getAccess_token() == null || result.getUser_seq_no() == null) return;

                User user = new User();
                UserDao roomUserDao = AppDatabase.getInstance(this).roomUserDao();
                user.setUserSeqNo(result.getUser_seq_no());
                user.setAccessToken(result.getAccess_token());

                AccessDBTask task = new AccessDBTask(roomUserDao);
                task.execute(user);

//                List<User> userList = roomUserDao.getAll();
//                System.out.println(userList.toString());
//                Intent intent = new Intent(Login_API2.this,  MainActivity.class);
//                startActivity(intent);
            } else {
                finish();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    Button.OnClickListener goMain = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Login_API2.this,  activity_main.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    };

    public static class NetWorkTask extends AsyncTask<Void, Void, AuthVO> {
        private final String stringfiedJson;
        private String url;

        public NetWorkTask(String url, String stringfiedJson) {
            this.url = url;
            this.stringfiedJson = stringfiedJson;
        }
        //execute한 후에 백그라운드 쓰레드에서 호출됨

        @Override
        protected AuthVO doInBackground(Void... params) {
            AuthVO result = null;
            try {
                URL uri = new URL(url);
                java.net.HttpURLConnection connection = (java.net.HttpURLConnection) uri.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("charset", "UTF-8");
                connection.setUseCaches(false);
                connection.setDoInput(true); //body에 값을 넣을 건지
                connection.setDoOutput(true); //값을 반환 받을건지
                OutputStream os = connection.getOutputStream();
                os.write(stringfiedJson.getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();

                InputStream is = connection.getInputStream();

                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append('\n');
                }

                result = new Gson().fromJson(builder.toString(), AuthVO.class);

                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

    }

    private class AccessDBTask extends AsyncTask<User, Void, Void> {
        private final UserDao roomUserDao;

        public AccessDBTask(UserDao roomUserDao) {
            this.roomUserDao = roomUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            for(User user: users)
                roomUserDao.add(user);
            final String result = roomUserDao.getAll().get(0).getAccessToken();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_API2.this, result, Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }
}

