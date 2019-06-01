package com.example.newsapp;

class News {

    private String mTitle;
    private String mUrl;
    private String mSection;
    private String mDate;
    private String mAuthor;

    News(String title, String url, String section, String date, String autor) {
        this.mTitle = title;
        this.mUrl = url;
        this.mSection = section;
        this.mDate = date;
        this.mAuthor = autor;
    }

    public String getTitle() {
        return mTitle;
    }


    public String getUrl() {
        return mUrl;
    }

    public String getSection() {
        return mSection;
    }

    public String getDate() {
        return mDate;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
