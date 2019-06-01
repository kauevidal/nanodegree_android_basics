package com.example.android.tourguideapp.model;

public class Place {

    private String mName;
    private String mDescription;
    private int mImagePath;

    public Place(String name, String description, int imageId) {
        this.mName = name;
        this.mDescription = description;
        this.mImagePath = imageId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getImageId() {
        return mImagePath;
    }
}
