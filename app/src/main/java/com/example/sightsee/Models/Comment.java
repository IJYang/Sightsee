package com.example.sightsee.Models;

import com.example.sightsee.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class Comment {
    private String site_id;
    private String user_id;
    private String comment_text;

    public String getSite_id() { return site_id; }
    public void setSite_id(String site_id) { this.site_id = site_id; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getComment_text() { return comment_text; }
    public void setComment_text(String comment_text) { this.comment_text = comment_text; }

    public Comment() {
        // required empty constructor
    }

    public Comment(String comment_text, String site_id, String user_id) {
        this.site_id = site_id;
        this.user_id = user_id;
        this.comment_text = comment_text;
    }
}
