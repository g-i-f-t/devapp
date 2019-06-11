package com.example.activity_maim;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.activity_maim.splash.ProfileManager;

public class activity_main extends AppCompatActivity {

    private TextView funding;
    private TextView money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design_profile_screen_xml_ui_design);

        funding = findViewById(R.id.t1);

        funding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fundingIntent = new Intent(activity_main.this, fundlist.class);
                fundingIntent.putExtra("back", "main");
                activity_main.this.startActivity(fundingIntent);
            }

        });

        money =  findViewById(R.id.t4);

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moneyIntent = new Intent(activity_main.this,money.class);
                activity_main.this.startActivity(moneyIntent);
            }

        });
        setUserInfo();
    }
    private void setUserInfo(){
        String userInfo = ((GiftApplication) getApplication()).userInfo().getName();
        TextView userId = findViewById(R.id.user_profile_name);
        userId.setText(userInfo);
    }

}
