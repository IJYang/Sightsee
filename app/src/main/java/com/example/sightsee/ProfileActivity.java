package com.example.sightsee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sightsee.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView lv;

    NavigationView navigationView;
    ArrayList<User> userList;

    public DatabaseReference mDatabase;
    public String uid, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        tv_email.setText("Loading...");

        TextView tv_userType = (TextView) findViewById(R.id.tv_userType);
        tv_userType.setText("Loading...");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            tv_email.setText(email);

            uid = user.getUid();
        } else {
            tv_userType.setText("Error");
            tv_email.setText("Error");

        }

        mDatabase.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    DataSnapshot t = task.getResult();
                    HashMap<String, String> users = (HashMap) t.getValue();
                    for (Map.Entry mapElement : users.entrySet()) {
                        String key = (String)mapElement.getKey();

                        HashMap<String, String> u = (HashMap) mapElement.getValue();
                        String user_email = (String) u.get("user_email");
                        String user_type = (String) u.get("user_type");

                        if (user_email.equals(email)){
                            tv_userType.setText(user_type);
                        }
                    }
                }
            }
        });

        userList = new ArrayList<User>();
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
                                Toast.makeText(ProfileActivity.this, "Insufficient Privileges.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    drawer.closeDrawer(GravityCompat.START);
                }
                else if (id == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    drawer.closeDrawer(GravityCompat.START);
                }
                else if (id == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    drawer.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}