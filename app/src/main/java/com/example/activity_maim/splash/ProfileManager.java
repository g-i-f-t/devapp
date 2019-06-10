package com.example.activity_maim.splash;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.example.activity_maim.GiftApplication;
import com.example.activity_maim.NetworkTask;
import com.example.activity_maim.Room.AppDatabase;
import com.example.activity_maim.Room.UserDao;
import com.example.activity_maim.User;
import com.example.activity_maim.VO.LoginVO;
import com.example.activity_maim.VO.Profile;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ProfileManager {
    private NetworkTask netWorkTask;
    public ProfileManager() {
    }

    public interface Callback {
        public void callback();
    }
    public void stopTask() {
        if(!netWorkTask.isCancelled()) netWorkTask.cancel(true);
    }
    //해당 User의 시퀀스 넘버에 대한 userName과 userEmail을 반환
    public void getProfile(String loginKey, Activity activity, ProfileManager.Callback callback) {
        String url = "http://117.17.102.139:8080/developer/" + loginKey;
        netWorkTask = new NetworkTask(url, activity);
        LoginVO result;
        try {
                    result = netWorkTask.execute().get();
            System.out.println("@@@@@@@@@@@@@@@"+result.getCode());
                    if (result.getCode() == 200) {
//                        Profile profile = new Profile();
//                        profile.setName(result.getName());
//                        profile.setEmail(result.getEmail());
//
//                        ((GiftApplication) activity.getApplication()).setUserInfo(profile);
                callback.callback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(netWorkTask.getStatus() == AsyncTask.Status.RUNNING) {
                try {
                    netWorkTask.cancel(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //RoomDB에 저장된 시퀀스넘버 반환
    public Map<String, String> getLoginKey(Context context) {
        Map<String, String> sendData = null;
        User user = new User();
        UserDao roomUserDao = AppDatabase.getInstance(context).roomUserDao();

        // Room에서 get, user정보가 있을경우 서버로 로그인 요청
        try {
          sendData = new RoomLog.getDBTask(roomUserDao).execute(user).get();
            System.out.println("fdafdsa"+sendData);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sendData;
    }

    class NetWorkTask extends AsyncTask<Void, Void, LoginVO> {
        private String url;

        public NetWorkTask(String url) {
            this.url = url;
        }
        //execute한 후에 백그라운드 쓰레드에서 호출됨

        @Override
        protected  LoginVO doInBackground(Void... params) {
            LoginVO result = null;
            try {
                URL uri = new URL(url);

                HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                connection.setRequestMethod("GET");
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setRequestProperty("charset", "UTF-8");
//                connection.setUseCaches(false);
                InputStream is = connection.getInputStream();

                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append('\n');
                }

                result = new Gson().fromJson(builder.toString(), LoginVO.class);
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
}