package com.example.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;
    private static final String LOGGER = NewsLoader.class.getSimpleName();

    NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        try {
            return RestClient.getNews(mUrl);
        } catch (MalformedURLException e) {
            Log.e(LOGGER, "Malformed URL.", e);
            return Collections.emptyList();
        }
    }
}
