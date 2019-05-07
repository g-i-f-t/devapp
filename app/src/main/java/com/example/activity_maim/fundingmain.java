package com.example.activity_maim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class fundingmain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundingmain);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.fundprogress);
        progressBar.setMax(100);
        progressBar.setProgress(45);
    }
}
