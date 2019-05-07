package com.example.activity_maim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText idtext = (EditText) findViewById(R.id.idText);
        EditText passwordText = (EditText) findViewById(R.id.passwordtext);
        Button loginButtton = (Button) findViewById(R.id.loginButton);
        TextView registerButton = (TextView) findViewById(R.id.registerButton);

        loginButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(activity_login.this, activity_main.class);
                activity_login.this.startActivity(loginIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(activity_login.this, activity_register.class);
                activity_login.this.startActivity(registerIntent);
            }
        });
    }
}
