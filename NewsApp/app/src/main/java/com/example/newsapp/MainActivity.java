package com.example.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String API_KEY = "test";
    private static final String URL = "https://content.guardianapis.com/search";
    private static final Integer LOADER_ID = 1;
    private NewsAdapter mNewsAdapter;
    private List<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.news_list);
        TextView emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
        mNewsAdapter = new NewsAdapter(this, newsList);
        listView.setAdapter(mNewsAdapter);

        if (isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            View loadingView = findViewById(R.id.progress_bar);
            loadingView.setVisibility(View.GONE);
            emptyView.setText(R.string.ConnectionError);
        }
    }

    private boolean isConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        return new NewsLoader(this, buildRequest());
    }

    private String buildRequest() {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String settingsOrder = sharedPrefs.getString(getString(R.string.orderKey), getString(R.string.orderDefaultValue));
        String settingsSize = sharedPrefs.getString(getString(R.string.sizeKey), getString(R.string.pageDefaultValue));
        Uri baseUri = Uri.parse(URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", "football");
        uriBuilder.appendQueryParameter("order-by", settingsOrder);
        uriBuilder.appendQueryParameter("page-size", settingsSize);
        uriBuilder.appendQueryParameter("lang", "en");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", API_KEY);
        return uriBuilder.toString();
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {

        View loadingView = findViewById(R.id.progress_bar);
        loadingView.setVisibility(View.GONE);
        TextView emptyView = findViewById(R.id.empty_view);
        emptyView.setText(R.string.NoData);

        if (data != null && !data.isEmpty()) {
            mNewsAdapter.clear();
            emptyView.setVisibility(View.GONE);
            mNewsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

        mNewsAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


