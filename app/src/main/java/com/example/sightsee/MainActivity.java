package com.example.sightsee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.sightsee.Models.Site;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    ArrayList<Site> siteList;
    TextView tvLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.site_list);
        siteList = Site.get_test_sites();
        SiteAdapter adapter = new SiteAdapter(MainActivity.this, siteList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SiteDetailActivity.class);
                Site site = siteList.get(i);
                intent.putExtra("id", site.getId());
                intent.putExtra("name", site.getName());
                intent.putExtra("address", site.getAddress());
                intent.putExtra("image", site.getImageResourceId());
                startActivity(intent);
            }
        });

        tvLogout = findViewById(R.id.tvLogout);
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); // logout
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }
}