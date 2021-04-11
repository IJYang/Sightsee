package com.example.sightsee;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.sightsee.Models.Comment;
import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter<Comment> {

    Context _context;

    public CommentAdapter(Context context, ArrayList<Comment> comments) {
        super(context, 0, comments);
        _context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;

        Comment comment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_list, parent, false);
        }

        // Get the views
        TextView tv_username = convertView.findViewById(R.id.tvCommentUsername);
        TextView tv_message = convertView.findViewById(R.id.tvCommentMessage);

        // Inflate views with data
        // TODO: Fill with Firebase Comment Data
        tv_username.setText(comment.getUser_id());
        tv_message.setText(comment.getComment_text());

        return convertView;
    }
}
