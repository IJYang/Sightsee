package com.example.sightsee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.sightsee.Models.Comment;
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

public class CommentsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference databaseCases;
    ArrayList<Comment> comment_list;
    int siteId; // used for determining background colour?
    private ListView lv;

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
    }
}