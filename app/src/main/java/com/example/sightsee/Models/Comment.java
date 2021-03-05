package com.example.sightsee.Models;

public class Comment {
    private int id;
    private String username;
    private float rating;
    private String reviewText;
    private int userPictureResourceId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

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

    public Comment(int id, String username, float rating, String reviewText, int userPictureResourceId) {
        this.id = id;
        this.username = username;
        this.rating = rating;
        this.reviewText = reviewText;
        this.userPictureResourceId = userPictureResourceId;
    }


}
