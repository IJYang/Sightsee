package com.example.sightsee.Models;

import java.util.ArrayList;

public class Comment {
    private String date_added;
    private String message;
    private int rating;
    private int site_id;
    private String user_id;
    private String commentId;

    public String getCommentId() { return commentId; }
    public void setCommentId(String commentId) { this.commentId = commentId; }

    public int getSite_id() { return site_id; }
    public void setSiteId(int site_id) { this.site_id = site_id; }

    public String getUserId() { return user_id; }
    public void setUserId(String username) { this.user_id = user_id; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getDateAdded() { return date_added; }
    public void setDateAdded(String date_added) { this.date_added = date_added; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Comment() {}

    public Comment(String date_added, String message, int rating, int site_id, String user_id) {
//        this.commentId = commentId;
        this.site_id = site_id;
        this.user_id = user_id;
        this.rating = rating;
        this.date_added = date_added;
        this.message = message;
    }

    public static ArrayList<Comment> get_test_comments() {
        ArrayList<Comment> comment_list = new ArrayList<>();
//        Comment c1 = new Comment(0, 0, 0, 3, "2020-03-31", "review 1, siteId 0");
//        comment_list.add(c1);
//
//        Comment c2 = new Comment(1, 0, 0, 5, "2020-04-1", "Review 2, SiteID 0");
//        comment_list.add(c2);
//
//        Comment c3 = new Comment(2, 1, 0, 4, "2020-03-31", "Review 3, SiteID 1");
//        comment_list.add(c3);
//
//        Comment c4 = new Comment(3, 2, 0, 1, "2020-03-31", "Review 4, SiteID 2");
//        comment_list.add(c4);
//
//        Comment c5 = new Comment(4, 3, 0, 4, "2020-03-31", "Review 5, SiteID 3");
//        comment_list.add(c5);
//
//        Comment c6 = new Comment(5, 4, 0, 4, "2020-03-31", "Review 6, SiteID 4");
//        comment_list.add(c6);
//
//        Comment c7 = new Comment(6, 4, 0, 4, "2020-03-31", "Review 7, SiteID 4");
//        comment_list.add(c7);
        return comment_list;
    }


}
