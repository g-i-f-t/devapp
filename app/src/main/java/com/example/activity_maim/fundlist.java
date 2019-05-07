package com.example.activity_maim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class fundlist extends AppCompatActivity {
    private ArrayList<HashMap<String,String>> Data = new ArrayList<HashMap<String, String>>();
    private HashMap<String,String> InputData1 = new HashMap<>();
   private HashMap<String,String> InputData2 = new HashMap<>();
   private ListView listView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_fundlist);
               listView =(ListView)findViewById(R.id.listview1);


               InputData1.put("game","손이 절로가네 게임");
               InputData1.put("date","펀딩 기간 2019-05-20");
               Data.add(InputData1);

               InputData2.put("game","절로가는 손 게임");
               InputData2.put("date","펀딩 기간 2019-06-27");
               Data.add(InputData2);


               SimpleAdapter simpleAdapter = new SimpleAdapter(this,Data,android.R.layout.simple_list_item_2,new String[]{"game","date"},new int[]{android.R.id.text1,android.R.id.text2});
               listView.setAdapter(simpleAdapter);

               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       Intent fundinglistIntent = new Intent(fundlist.this,fundingmain.class);
                       fundlist.this.startActivity(fundinglistIntent);
                   }
               });
  }
}
