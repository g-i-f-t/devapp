package com.example.activity_maim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText idText =  (EditText) findViewById(R.id.idText);
        EditText passwordText = (EditText) findViewById(R.id.birthtext);
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
        Button funding = (Button) findViewById(R.id.funding);
        Button checkfunding = (Button) findViewById(R.id.checkfunding);
        Button editfunding = (Button) findViewById(R.id.editfunding);

        funding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fundingIntent = new Intent(activity_main.this,fundlist.class);
                activity_main.this.startActivity(fundingIntent);
            }

        });
        editfunding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editfundingIntent = new Intent(activity_main.this,fundlist.class);
                activity_main.this.startActivity(editfundingIntent);
            }

        });
        checkfunding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(activity_main.this, fundin.class);
                activity_main.this.startActivity(loginIntent);
            }
        });
    }
}
