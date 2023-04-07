package com.example.eod.models;

import java.util.ArrayList;

public class Post {
    public String text = "";
    public User createdBy = new User();
    public long createdAt;
    public ArrayList<String> likedBy = new ArrayList<>();

   public Post()
    {

    }

    public Post(String text, User createdBy, long createdAt, ArrayList<String> likedBy)
    {
        this.text = text;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.likedBy = likedBy;
    }

    public ArrayList<String> getLikedBy() {
        return likedBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    public User getCreatedBy() {
        return createdBy;
    }

}
