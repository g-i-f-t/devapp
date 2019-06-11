package com.example.activity_maim;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class activity_register extends AppCompatActivity {
    TextView birthText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordtext);
        final EditText nameText = (EditText) findViewById(R.id.nametext);
        birthText = (TextView) findViewById(R.id.birthtext);
        Button datesave = (Button) findViewById(R.id.datesave);
        final EditText sexText = (EditText) findViewById(R.id.sextext);

        GregorianCalendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        if (month <= 10) {
            birthText.setText("생년월일 : " + year + "0" + (month + 1) + day);

            if (day <= 10) {
                birthText.setText("생년월일 : " + year + "0" + (month + 1) + "0" + day); }
            }else if (day <= 10) {
                birthText.setText("생년월일 : " + year + (month + 1) + "0" + day);
            }
            else {
                birthText.setText("생년월일 : " + year + (month + 1) + day);
            }

        birthText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.activity_maim.bir_insert.class);
                startActivityForResult(intent, 1001);
            }
        });

        datesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_ok = idText.getText().toString();
                String password_ok = passwordText.getText().toString();
                String name_ok = nameText.getText().toString();
                String birth_ok = birthText.getText().toString();
                String sex_ok = sexText.getText().toString();

                Toast.makeText(activity_register.this, "아이디: " + id_ok + "\n이름 :" + name_ok  + "\n"+ birth_ok + "\n성별 :" + sex_ok, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String bir_year = data.getStringExtra("year_ok");
        String bir_month = data.getStringExtra("month_ok");
        String bir_day = data.getStringExtra("day_ok");

        birthText.setText("생년월일 : "+bir_year+bir_month+bir_day);
    }}
