/**
 * Author Aerain
 * SSLAB
 * Jeju National University
 */

package com.example.activity_maim.splash;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity_maim.R;
import com.example.activity_maim.activity_login;
import com.example.activity_maim.activity_main;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {
    private ProfileManager profileManager;
    private AlertDialog.Builder alert;
    private Timer limitLoadingTimer;
    private ImageView imgAndroid;
    private Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        profileManager = new ProfileManager();
        limitLoadingTimer = new Timer();
        initView();
        makeAlert();
        LogSearch();
    }

    private void initView() {
        imgAndroid =  findViewById(R.id.loadingView);
        anim = AnimationUtils.loadAnimation(this, R.anim.loading);
                imgAndroid.setAnimation(anim);
    }
    private void makeAlert() {
        alert = new AlertDialog.Builder(this);
        alert.setTitle("알림");
        alert.setMessage("GIFT서버와 연결이 되지 않습니다!");
        alert.setPositiveButton("확인", (dialog, which) -> {
            dialog.dismiss();     //닫기
            finish();
        });
    }
    private void LogSearch() {
        String LoginKey = profileManager.getLoginKey(this).get("userSeqNo");
            if (LoginKey != null) { // 한번이라도 접속한 적이 있을때. 자동로그인 시도
                limitLoadingTimer.schedule(new TimerTask() { // 이때 타이머를 재. 왜잴까? 너무 로딩시간이 길면 접속 끊을라고.
                    public void run() {
                        profileManager.stopTask();
                        runOnUiThread(() -> alert.show());
                    }
                }, 5000); // 5초가 마지노선.
                //Room에 유저 정보가 있다면 goMain!
                profileManager.getProfile(LoginKey, this, () -> {
                    limitLoadingTimer.cancel();
                    Intent intent = new Intent(SplashActivity.this, activity_main.class);
                    startActivity(intent);
                    finish();
                });
            }
            else {
                Intent intent = new Intent(this, activity_login.class);
                startActivity(intent);
                finish();
            }
    }
}
