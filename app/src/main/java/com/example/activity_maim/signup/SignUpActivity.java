package com.example.activity_maim.signup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.activity_maim.R;

import java.util.HashMap;


public class SignUpActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ActionBar actionBar;

    AlertDialog.Builder alert;

    EditText et_email, et_pw, et_pw_chk, et_name;
    String email, password, password_chk, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void load_Api(View view) {
        /* 버튼을 눌렀을 때 동작하는 소스 */
        email = et_email.getText().toString();
        password = et_pw.getText().toString();
        password_chk = et_pw_chk.getText().toString();
        name = et_name.getText().toString();

        if(!email.equals("") && !password.equals("") && !name.equals("") ){
            if(password.equals(password_chk)) {
                /* 패스워드 확인이 정상적으로 됨 */
                HashMap<String, String> extra = new HashMap<>();
                extra.put("name", name);
                extra.put("email",email);
                extra.put("password", HashService.sha256(password));

                Intent intent=new Intent(SignUpActivity.this, Login_API.class);
                intent.putExtra("data", extra);
                startActivity(intent);
                finish();
            }
            else {
                alert.setMessage("비밀번호를 확인해주십시오.");
                alert.show();
            }
        }
        else {
            alert.setMessage("양식에 맞게 입력해주세요.");
            alert.show();
        }
    }
    private AlertDialog.Builder showAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
            }
        });
        return alert;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = findViewById(R.id.login_toolbar);
        alert = showAlert();
        et_email = findViewById(R.id.login_id_editText);
        et_pw = findViewById(R.id.login_pwd_editText);
        et_pw_chk = findViewById(R.id.login_pwd_chk_editText);
        et_name = findViewById(R.id.login_name_editText);
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


}