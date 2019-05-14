package com.example.activity_maim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class fundingmain1 extends AppCompatActivity {

    private TextView withdraw;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundingmain1);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.fundprogress);
        progressBar.setMax(100);
        progressBar.setProgress(100);

        withdraw =  findViewById(R.id.wd);

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wdIntent = new Intent(fundingmain1.this,withdraw.class);
                fundingmain1.this.startActivity(wdIntent);
            }



        });
    }
}