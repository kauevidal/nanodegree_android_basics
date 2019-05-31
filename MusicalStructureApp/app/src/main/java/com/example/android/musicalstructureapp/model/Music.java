package com.example.android.musicalstructureapp.model;

public class Music {

    private String mName;
    private Artist mArtist;

    public Music(String name, Artist artist) {
        this.mName = name;
        this.mArtist = artist;
    }

    public String getName() {
        return mName;
    }

    public Artist getArtist() {
        return mArtist;
    }
}
