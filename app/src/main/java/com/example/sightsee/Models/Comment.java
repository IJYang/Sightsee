package com.example.sightsee.Models;

import com.example.sightsee.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class Comment {
    private int siteId;
    private int userId;
    private int rating;
    private LocalDate date;
    private String message;

    public int getSiteId() { return siteId; }
    public void setSiteId(int siteId) { this.siteId = siteId; }

    public int getUserId() { return userId; }
    public void setUserId(String username) { this.userId = userId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Comment(int siteId, int userId, int rating, LocalDate date, String message) {
        this.siteId = siteId;
        this.userId = userId;
        this.rating = rating;
        this.date = date;
        this.message = message;
    }

    public static ArrayList<Comment> get_test_comments() {
        ArrayList<Comment> comment_list = new ArrayList<>();
        Comment c1 = new Comment(0, 0, 3, LocalDate.of(2020, 03, 31), "review 1, siteId 0");
        comment_list.add(c1);

        Comment c2 = new Comment(0, 0, 5, LocalDate.of(2020, 04, 1), "Review 2, SiteID 0");
        comment_list.add(c2);

        Comment c3 = new Comment(1, 0, 4, LocalDate.of(2020, 03, 31), "Review 3, SiteID 1");
        comment_list.add(c3);

        Comment c4 = new Comment(2, 0, 1, LocalDate.of(2020, 03, 31), "Review 4, SiteID 2");
        comment_list.add(c4);

        Comment c5 = new Comment(3, 0, 4, LocalDate.of(2020, 03, 31), "Review 5, SiteID 3");
        comment_list.add(c5);

        Comment c6 = new Comment(4, 0, 4, LocalDate.of(2020, 03, 31), "Review 6, SiteID 4");
        comment_list.add(c6);

        Comment c7 = new Comment(4, 0, 4, LocalDate.of(2020, 03, 31), "Review 7, SiteID 4");
        comment_list.add(c7);
        return comment_list;
    }


}
