package com.example.android.musicalstructureapp.model;

public class Artist {

    private int mId;
    private String mName;
    private String mDescription;

    public Artist(int id, String name, String description) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }
}
