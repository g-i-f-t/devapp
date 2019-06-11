package com.example.activity_maim;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;


public class fundingmain extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundingmain);

        RoundCornerProgressBar progress1 = (RoundCornerProgressBar) findViewById(R.id.progress_1);
        progress1.setProgressBackgroundColor(Color.parseColor("#ddd9da"));
        progress1.setProgressColor(Color.parseColor("#ffe100"));

        progress1.setMax(100);
        progress1.setProgress(45);

        if (progress1.getProgress() < 25){
            progress1.setProgressColor(Color.parseColor("#ffe100"));
        }else if(progress1.getProgress() < 50) {
            progress1.setProgressColor(Color.parseColor("#0400ff"));
        }
        else if(progress1.getProgress() < 75) {
            progress1.setProgressColor(Color.parseColor("#d81b60"));
        }
        else {
            System.out.println("10000000000000000000"+progress1.getProgress());
            progress1.setProgressColor(Color.parseColor("#1ed81b"));
        }
    }
}
