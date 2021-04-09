package com.example.sightsee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.sightsee.Models.Comment;
import com.example.sightsee.Models.Promotion;
import com.example.sightsee.Models.Site;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference databaseCases;
    ArrayList<Promotion> promo_list;
    int siteId;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        promo_list = new ArrayList<Promotion>();
        ListView lv = findViewById(R.id.promotion_list);
        String site_id = (String) getIntent().getExtras().get("site_id");

        databaseCases = FirebaseDatabase.getInstance().getReference();
        databaseCases.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                promo_list.clear();
                for (DataSnapshot caseSnapshot: dataSnapshot.child("promotions").getChildren()) {
                    Promotion promotion = caseSnapshot.getValue(Promotion.class);
                    if (promotion.getSiteId().equals(site_id)) {
                        promo_list.add(promotion);
                    }
                }
                PromotionAdapter adapter = new PromotionAdapter(PromotionActivity.this, promo_list);
                lv.setEmptyView(findViewById(android.R.id.empty));
                lv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        // Don't need any click handlers yet
    }
}