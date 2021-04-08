package com.example.sightsee.Models;

public class CommentUpload {
    private String user_id;
    private String site_id;
    private String comment_text;


    public CommentUpload() {
        // required empty constructor.
    }

    public CommentUpload(String user_id, String site_id, String comment_text) {
        if (comment_text.trim().equals("")) {
            comment_text = "Missing Comment";
        }
        this.user_id = user_id;
        this.site_id = site_id;
        this.comment_text = comment_text;
    }

    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    };

    public String getSite_id() {
        return site_id;
    }
    public void setSite_id(String site_id) {
        site_id = site_id;
    }

    public String getComment_text() { return comment_text; }
    public void setComment_text(String comment_text) { this.comment_text = comment_text; }
}
