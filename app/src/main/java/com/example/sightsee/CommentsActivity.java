package com.example.sightsee;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.sightsee.Models.Comment;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    int siteId;
    private ListView lv;
    ArrayList<Comment> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        // site id must be passed in
        Bundle extras = getIntent().getExtras();
        siteId = (extras != null) ? (Integer.parseInt(extras.getString("siteId"))) : null;

        lv = findViewById(R.id.commentList);
        commentList = Comment.get_test_comments();
        CommentAdapter adapter = new CommentAdapter(CommentsActivity.this, commentList);
        lv.setAdapter(adapter);

        // Don't need any click handlers yet
    }
}