package com.example.sightsee;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sightsee.Models.Site;

import androidx.appcompat.app.AppCompatActivity;

public class SiteDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_site_details);

        int id = (Integer) getIntent().getExtras().get("id");
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
}
