package com.example.sightsee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sightsee.Models.Site;
import com.example.sightsee.Models.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView lv;
    ArrayList<Site> siteList;
    TextView tvLogout;
    TextView tvProfile;
    NavigationView navigationView;
    DatabaseReference databaseCases;
    private FirebaseAuth mAuth;
    ArrayList<User> userList;
    Map<Site, String> map = new HashMap<Site, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        lv = findViewById(R.id.site_list);
        siteList = new ArrayList<Site>();
        userList = new ArrayList<User>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference test = mDatabase.child("users");
        test.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item_snapshot:dataSnapshot.getChildren()) {
                    User user = item_snapshot.getValue(User.class);
                    userList.add(user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_logout) {
                    FirebaseAuth.getInstance().signOut(); // logout
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                else if (id == R.id.add_location) {
                    String user_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    for (User user: userList) {
                        if (user.user_email.equals(user_email)) {
                            String user_type = user.user_type;
                            if (user_type.equals("admin") && id == R.id.add_location) {
                                startActivity(new Intent(getApplicationContext(), AddSiteActivity.class));
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Insufficient Privileges.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    drawer.closeDrawer(GravityCompat.START);
                }
                else if (id == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    drawer.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
        FirebaseUser currentUser = mAuth.getCurrentUser();
        databaseCases = FirebaseDatabase.getInstance().getReference();
        databaseCases.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                siteList.clear();
                for (DataSnapshot caseSnapshot: dataSnapshot.child("sites").getChildren()) {
                    Site site = caseSnapshot.getValue(Site.class);
                    map.put(site, caseSnapshot.getKey());
                    siteList.add(site);
                }
                SiteAdapter adapter = new SiteAdapter(MainActivity.this, siteList);
                lv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        SiteAdapter adapter = new SiteAdapter(MainActivity.this, siteList);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SiteDetailActivity.class);
                Site site = siteList.get(i);
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                String site_id = map.get(site);
                String display_name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                String user_id = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                intent.putExtra("site_id", site_id);
                intent.putExtra("user_id", user_id);
                intent.putExtra("name", site.getName());
                intent.putExtra("address", site.getAddress());
                intent.putExtra("site_type", site.getType());
                intent.putExtra("price", site.getPrice());
                intent.putExtra("imageUrl", site.getImageUrl());
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void removeHeader(View view) {
        CardView header = findViewById(R.id.main_header);
        ListView site_list = findViewById(R.id.site_list);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,25,0,0);
        site_list.setLayoutParams(params);
        header.setVisibility(View.GONE);
    }

}