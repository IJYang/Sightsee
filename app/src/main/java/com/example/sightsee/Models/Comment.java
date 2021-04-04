package com.example.sightsee.Models;

import com.example.sightsee.R;

import java.util.ArrayList;

public class Comment {
    private int siteId;
    private int userId;
    private int rating;
    private String message;

    public int getSiteId() { return siteId; }
    public void setSiteId(int siteId) { this.siteId = siteId; }

    public int getUserId() { return userId; }
    public void setUserId(String username) { this.userId = userId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Comment(int siteId, int userId, int rating, String message) {
        this.siteId = siteId;
        this.userId = userId;
        this.rating = rating;
        this.message = message;
    }

    public static ArrayList<Comment> get_test_comments() {
        ArrayList<Comment> comment_list = new ArrayList<>();
        Comment c1 = new Comment(0, 0, 3, "review 1 hello");
        comment_list.add(c1);

        return comment_list;
    }


}
