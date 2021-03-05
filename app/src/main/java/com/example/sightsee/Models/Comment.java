package com.example.sightsee.Models;

import java.util.ArrayList;

public class Comment {
    private int id;
    private int siteId;
    private String username;
    private float rating;
    private String reviewText;
    private int userPictureResourceId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSiteId() { return siteId; }
    public void setSiteId(int siteId) { this.siteId = siteId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public int getUserPictureResourceId() { return userPictureResourceId; }
    public void setUserPictureResourceId(int userPictureResourceId) {
        this.userPictureResourceId = userPictureResourceId;
    }

    public Comment(int id, int siteId, String username, float rating, String reviewText, int userPictureResourceId) {
        this.id = id;
        this.username = username;
        this.rating = rating;
        this.reviewText = reviewText;
        this.userPictureResourceId = userPictureResourceId;
    }

    public static ArrayList<Comment> get_test_comments() {
        ArrayList<Comment> comment_list = new ArrayList<>();
        Comment c1 = new Comment(0, 0, "TaylorSwiftFan42", 3.3,
                "review 1 hello",
                );



    }


}
