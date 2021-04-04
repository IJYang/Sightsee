package com.example.sightsee;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sightsee.Models.Site;

public class SiteDetailActivity extends AppCompatActivity {

    private Button moreCommentsBtn;
    int siteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_site_details);

        siteId = (Integer) getIntent().getExtras().get("id");
        String name = (String) getIntent().getExtras().get("name");
        String address = (String) getIntent().getExtras().get("address");
        int imageId = (Integer) getIntent().getExtras().get("image");

        ImageView photo = findViewById(R.id.siteImage);
        photo.setImageResource(imageId);
//        photo.setContentDescription(food.toString());

        // Display product information
        TextView nametv = findViewById(R.id.siteName);
        nametv.setText(name);

        TextView addresstv = findViewById(R.id.siteAddress);
        addresstv.setText("Address: " + address);
    }

    public void moreComments(View view) {
        Intent intent = new Intent(SiteDetailActivity.this, CommentsActivity.class);
        intent.putExtra("siteId", String.valueOf(siteId));
        startActivity(intent);
    }
}
