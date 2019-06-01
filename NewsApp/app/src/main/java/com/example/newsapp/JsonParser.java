package com.example.newsapp;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

final class JsonParser {

    private static final String LOGGER = RestClient.class.getSimpleName();
    private static final String EMPTY_STRING = "";

    static List<News> parseJson(@NonNull String json) {

        List<News> newsList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject response = root.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject result = results.getJSONObject(i);
                String title = result.getString("webTitle");
                String section = result.getString("sectionName");
                String date = result.getString("webPublicationDate");
                String url = result.getString("webUrl");
                String author = EMPTY_STRING;
                if (result.has("tags")) {
                    JSONArray tags = result.getJSONArray("tags");
                    JSONObject tag = tags.getJSONObject(0);
                    author = tag.getString("webTitle");
                }
                newsList.add(new News(title, url, section, date, author));
            }
        } catch (JSONException e) {
            Log.e(LOGGER, "Error while trying parse JSON API response", e);
        }
        return newsList;
    }
}
