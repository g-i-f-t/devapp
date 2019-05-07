package com.example.activity_maim;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class bir_insert extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        //사용할 타입에 맞는 변수를 선언 해주고 연결 해줍니다
        final EditText year = (EditText)findViewById(R.id.year);
        final EditText month = (EditText)findViewById(R.id.month);
        final EditText day = (EditText)findViewById(R.id.day);

        Button bir_sub = (Button)findViewById(R.id.bir_sub);
        Button bir_cansle = (Button)findViewById(R.id.bir_cansle);
        //설정 버튼을 누르게 되면 각 값을 읽어서 변수에 담아주고
        //그 변수를 putExtra에 싫어서 setResult로 날려줍니다
        bir_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String year_ok = year.getText().toString();
                String month_ok = month.getText().toString();
                String day_ok = day.getText().toString();

                if(month_ok.length() == 1) month_ok = 0 + month_ok;
                if(day_ok.length() == 1) day_ok = 0 + day_ok;

                Intent intent = new Intent();
                intent.putExtra("year_ok",year_ok);
                intent.putExtra("month_ok",month_ok);
                intent.putExtra("day_ok",day_ok);
                setResult(0, intent);
                finish();
            }
        });
        //닫기 버튼을 누르게 되면 finish 해줍니다
        bir_cansle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}