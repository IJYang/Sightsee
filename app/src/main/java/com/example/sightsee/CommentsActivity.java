package com.example.sightsee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sightsee.Models.Comment;
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
import java.util.List;
import java.util.stream.Collectors;

public class CommentsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    DatabaseReference databaseCases;
    ArrayList<Comment> comment_list;
    int siteId; // used for determining background colour?
    private ListView lv;

    NavigationView navigationView;
    ArrayList<User> userList;
    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        ArrayList<Comment> comment_list = new ArrayList<Comment>();
        ListView lv = findViewById(R.id.commentList);
        String site_id = (String) getIntent().getExtras().get("site_id");

        databaseCases = FirebaseDatabase.getInstance().getReference();
        databaseCases.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comment_list.clear();
                for (DataSnapshot caseSnapshot: dataSnapshot.child("comments").getChildren()) {
                    Comment comment = caseSnapshot.getValue(Comment.class);
                    if (comment.getSite_id().equals(site_id)) {
                        comment_list.add(comment);
                    }
                }
                CommentAdapter adapter = new CommentAdapter(CommentsActivity.this, comment_list);
                lv.setEmptyView(findViewById(android.R.id.empty));
                lv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        // Don't need any click handlers yet

        mDatabase = FirebaseDatabase.getInstance().getReference();
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
                                Toast.makeText(CommentsActivity.this, "Insufficient Privileges.", Toast.LENGTH_SHORT).show();
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