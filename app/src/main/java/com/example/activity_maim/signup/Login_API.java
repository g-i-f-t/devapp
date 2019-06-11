package com.example.activity_maim.signup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.activity_maim.R;

import java.util.HashMap;
import java.util.Map;


public class Login_API extends AppCompatActivity {
    //TODO refactorying하기
    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = findViewById(R.id.login_toolbar);

        setToolBar();
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.arrow_back);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__api);

        Intent prevIntent = getIntent();
        final HashMap<String, String> extra = (HashMap<String, String>) prevIntent.getSerializableExtra("data");
        String Client_ID = "l7xx7ca57b2adb1340a5b5c982e87fbc44b1";
        WebView webview = findViewById(R.id.API_View);
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        StringBuilder uri = new StringBuilder("https://testapi.open-platform.or.kr/oauth/2.0/authorize2")
                .append("?response_type=code")
                .append("&client_id=")
                .append(Client_ID)
                .append("&redirect_uri=http://117.17.102.139:8080/auth")
                .append("&scope=login+inquiry+transfer")
                .append("&client_info=test")
                .append("&auth_type=0")
                .append("&bg_color=#ffffff")
                .append("&txt_color=#a3a3a3")
                .append("&btn1_color=#2e7d32")
                .append("&btn2_color=#000000");

        System.out.println(uri.toString());
        webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                System.out.println(url);
                if(url.contains("8080/auth?")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Login_API.this, Login_API2.class);
                    extra.put("code", uri.getQueryParameter("code"));
                    extra.put("scope", uri.getQueryParameter("scope"));
                    extra.put("client_info", uri.getQueryParameter("client_info"));
                    intent.putExtra("data", extra);
                    startActivity(intent);
                    finish();
                }
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(uri.toString(), headers);
    }


}