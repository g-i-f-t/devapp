package com.example.activity_maim;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class money extends AppCompatActivity {

    private Funding_list_adapter1 adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        View.OnClickListener add1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(money.this, fundin.class);
                money.this.startActivity(addIntent);
            }
        };

        init();
        getData();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new Funding_list_adapter1(new Funding_list_adapter.Callback() {

            @Override
            public void onCallback() {
                Intent itemIntent = new Intent(money.this, fundingmain1.class);
                startActivity(itemIntent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("" +
                        "RunRace3D",
                "TinkerIsland",
                "NonStop Knight",
                "스도쿠",
                "Racing Moto",
                "쿠킹다이어리",
                "Real Piano",
                "포커"

        );
        List<String> listContent = Arrays.asList(
                "스포츠",
                "어드벤처",
                "롤플레잉",
                "퍼즐",
                "레이싱",
                "시뮬레이션",
                "음악",
                "카드"

        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.l1,
                R.drawable.l2,
                R.drawable.l3,
                R.drawable.l4,
                R.drawable.l5,
                R.drawable.l6,
                R.drawable.l7,
                R.drawable.l8

        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}

