package com.example.sightsee;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.sightsee.Models.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Comment> fullCommentList = Comment.get_test_comments().stream()
                .filter(comment -> comment.getSiteId() == siteId)
                .collect(Collectors.toList());
        commentList = (ArrayList<Comment>) fullCommentList;

        lv = findViewById(R.id.commentList);
        CommentAdapter adapter = new CommentAdapter(CommentsActivity.this, commentList);
        lv.setAdapter(adapter);

        // Don't need any click handlers yet
    }
}