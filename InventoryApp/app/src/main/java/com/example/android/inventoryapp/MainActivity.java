package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import static data.ProductContract.ProductEntry;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUCT_LOADER = 0;
    private ProductCursorAdapter mProductCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.add_button)
                .setOnClickListener(view ->
                        startActivity(new Intent(MainActivity.this, EditActivity.class)));

        ListView listView = findViewById(R.id.product_list);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
        mProductCursorAdapter = new ProductCursorAdapter(this, null);
        listView.setAdapter(mProductCursorAdapter);
        getLoaderManager().initLoader(PRODUCT_LOADER, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(this, ProductEntry.CONTENT_URI, null,
                null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mProductCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mProductCursorAdapter.swapCursor(null);
    }
}
