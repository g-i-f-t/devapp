package com.example.activity_maim;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

public class fundingmain1 extends AppCompatActivity {

    private TextView withdraw;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundingmain1);

        RoundCornerProgressBar progress2 = (RoundCornerProgressBar) findViewById(R.id.progress_2);
        progress2.setProgressColor(Color.parseColor("#d81b60"));
        progress2.setProgressBackgroundColor(Color.parseColor("#ddd9da"));
        progress2.setMax(100);
        progress2.setProgress(100);

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