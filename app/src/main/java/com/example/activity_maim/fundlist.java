package com.example.activity_maim;

import android.content.Intent;

import com.example.activity_maim.VO.GameVO;
import com.example.activity_maim.adapter.Funding_list_adapter;
import com.example.activity_maim.splash.ProfileManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class fundlist extends AppCompatActivity {
    private Funding_list_adapter adapter;
    private ProfileManager profileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundlist);
        profileManager = ((GiftApplication) getApplication()).getProfileManager();

        View.OnClickListener add1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(fundlist.this, AddGames.class);
                fundlist.this.startActivity(addIntent);
            }
        };
        FloatingActionButton floatingActionButton = findViewById(R.id.add1);
        floatingActionButton.setOnClickListener(add1);
        init();
        getData();
    }


    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Funding_list_adapter(new Funding_list_adapter.Callback() {

            @Override
            public void onCallback() {
                Intent itemIntent = new Intent(fundlist.this, fundingmain.class);
                startActivity(itemIntent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        String userSeqId = profileManager.getLoginKey(this).get("userSeqNo");
        String url = "http://117.17.102.139:8080/game/developer/" + userSeqId;
        FundingTask fundingTask = new FundingTask(url);
        try {
            List<GameVO> result = fundingTask.execute().get();
            for(GameVO gameVO : result)
                System.out.println(gameVO.getName());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class FundingTask extends AsyncTask<Void, Void, List<GameVO>> {
        private final String url;

        private FundingTask(String url) {
            this.url = url;
        }

        @Override
        protected List<GameVO> doInBackground(Void... params) {
            List<GameVO> result = null;
            try {
                URL uri = new URL(url);

                HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                connection.setRequestMethod("GET");
                InputStream is = connection.getInputStream();

                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append('\n');
                }
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(builder.toString()).getAsJsonObject().get("data");
                Type type = new TypeToken<List<GameVO>>(){}.getType();
                result = gson.fromJson(element, type);
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
//        // 임의의 데이터입니다.
//        List<String> listTitle = Arrays.asList("" +
//                "RunRace3D",
//                "TinkerIsland",
//                "NonStop Knight",
//                "스도쿠",
//                "Racing Moto",
//                "쿠킹다이어리",
//                "Real Piano",
//                "포커"
//
//        );
//        List<String> listContent = Arrays.asList(
//                "스포츠",
//                "어드벤처",
//                "롤플레잉",
//                "퍼즐",
//                "레이싱",
//                "시뮬레이션",
//                "음악",
//                "카드"
//
//        );
//        List<Integer> listResId = Arrays.asList(
//                R.drawable.l1,
//                R.drawable.l2,
//                R.drawable.l3,
//                R.drawable.l4,
//                R.drawable.l5,
//                R.drawable.l6,
//                R.drawable.l7,
//                R.drawable.l8
//
//        );
//        for (int i = 0; i < listTitle.size(); i++) {
//            // 각 List의 값들을 data 객체에 set 해줍니다.
//            Data data = new Data();
//            data.setTitle(listTitle.get(i));
//            data.setContent(listContent.get(i));
//            data.setResId(listResId.get(i));
//
//            // 각 값이 들어간 data를 adapter에 추가합니다.
//            adapter.addItem(data);
//        }
//
//        // adapter의 값이 변경되었다는 것을 알려줍니다.
//        adapter.notifyDataSetChanged();
//    }

/*public class fundlist<linearLayoutManager> extends AppCompatActivity {
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
          title.add("손이 절로가네 게임1");
          title.add("손이 절로가네 게임2");

          title.add("손이 절로가네 게임3");title.add("손이 절로가네 게임3");
          title.add("손이 절로가네 게임3");title.add("손이 절로가네 게임3");
          title.add("손이 절로가네 게임3");title.add("손이 절로가네 게임3");
          title.add("손이 절로가네 게임3");title.add("손이 절로가네 게임3");
      }
      Funding_list_adapter adapter=new Funding_list_adapter(fundlist.this,title);

      RecyclerView view=findViewById(R.id.recyclerview);
      view.setAdapter(adapter);
      view.setLayoutManager(new LinearLayoutManager(fundlist.this));

  }
}
*/
