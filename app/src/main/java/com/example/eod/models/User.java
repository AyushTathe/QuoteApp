package com.example.eod.models;

import android.net.Uri;

public class User {
    public String uid = " ";
    public String displayName = " ";
    public String imageUrl;

    public User()
    {

    }

    public User(String uid, String displayName, String imageUrl) {
    this.uid = uid;
    this.displayName = displayName;
    this.imageUrl = String.valueOf(imageUrl);
    }

    public String getUid() {
        return uid;
    }
    public String getDisplayName()
    {
        return displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

