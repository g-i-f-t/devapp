package com.example.activity_maim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity_main extends AppCompatActivity {

    private TextView funding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design_profile_screen_xml_ui_design);

        funding =  findViewById(R.id.t1);

        funding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fundingIntent = new Intent(activity_main.this,fundlist.class);
                activity_main.this.startActivity(fundingIntent);
            }

        });
    }
}
