package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        final News currentNews = getItem(position);

        if (currentNews != null) {

            TextView titleView = convertView.findViewById(R.id.news_title);
            titleView.setText(currentNews.getTitle());

            TextView sectionView = convertView.findViewById(R.id.news_section);
            sectionView.setText(currentNews.getSection());

            TextView authorView = convertView.findViewById(R.id.news_author);
            authorView.setText(currentNews.getAuthor());

            TextView dateView = convertView.findViewById(R.id.news_date);
            dateView.setText(currentNews.getDate());

            convertView.setOnClickListener(v -> {
                Uri uri = Uri.parse(currentNews.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                getContext().startActivity(intent);
            });
        }

        return convertView;
    }
}
