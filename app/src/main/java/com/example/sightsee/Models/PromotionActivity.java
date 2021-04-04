package com.example.sightsee.Models;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sightsee.PromotionAdapter;
import com.example.sightsee.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionActivity extends AppCompatActivity {

    int siteId;
    private ListView lv;
    ArrayList<Promotion> promotionArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        // Site ID must be passed in
        Bundle extras = getIntent().getExtras();
        siteId = (extras != null) ? (Integer.parseInt(extras.getString("siteId"))) : null;

        List<Promotion> fullPromoList = Promotion.get_test_promotions().stream()
                .filter(promotion -> promotion.getSiteId() == siteId)
                .collect(Collectors.toList());
        promotionArrayList = (ArrayList<Promotion>) fullPromoList;

        lv = findViewById(R.id.promotion_list);
        PromotionAdapter adapter = new PromotionAdapter(PromotionActivity.this, promotionArrayList);
        lv.setAdapter(adapter);

        // Don't need click handlers yet

    }
}