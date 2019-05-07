package com.example.activity_maim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
   private ArrayList<String> title;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_fundlist);
      title=new ArrayList<>();
      for(int i=0;i<6;i++){
          title.add("손이 절로가네 게임");
      }
      Funding_list_adapter adapter=new Funding_list_adapter(fundlist.this,title);

      RecyclerView view=findViewById(R.id.recyclerview);
      view.setAdapter(adapter);
      view.setLayoutManager(new LinearLayoutManager(fundlist.this));

  }
}
