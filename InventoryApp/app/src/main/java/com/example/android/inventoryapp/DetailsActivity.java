package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import static data.ProductContract.ProductEntry.COLUMN_PRODUCT_NAME;
import static data.ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE;
import static data.ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY;
import static data.ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME;
import static data.ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE;

public class DetailsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DETAILS_LOADER = 0;

    private Uri mUri;
    private TextView mProductName;
    private TextView mProductPrice;
    private TextView mProductQuantity;
    private TextView mSupplierName;
    private TextView mSupplierPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle(getString(R.string.details_title));
        Intent intent = getIntent();
        mUri = intent.getData();
        getLoaderManager().initLoader(DETAILS_LOADER, null, this);

        mProductName = findViewById(R.id.edit_product_name);
        mProductPrice = findViewById(R.id.edit_product_price);
        mProductQuantity = findViewById(R.id.edit_product_quantity);
        mSupplierName = findViewById(R.id.edit_supplier_name);
        mSupplierPhone = findViewById(R.id.edit_supplier_number);

        findViewById(R.id.edit_button)
                .setOnClickListener(v -> startActivity(new Intent(DetailsActivity.this, EditActivity.class).setData(mUri)));

        findViewById(R.id.back_button)
                .setOnClickListener(v -> startActivity(new Intent(DetailsActivity.this, MainActivity.class).setData(mUri)));

        findViewById(R.id.plus_button)
                .setOnClickListener(v -> {
                    Integer productQuantity = Integer.parseInt(String.valueOf(mProductQuantity.getText()).trim());
                    productQuantity++;
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_PRODUCT_QUANTITY, productQuantity);
                    if (getContentResolver().update(mUri, values, null, null) == 0) {
                        showToast(R.string.update_error);
                    } else {
                        showToast(R.string.update_success);
                    }
                });

        findViewById(R.id.minus_button)
                .setOnClickListener(v -> {
                    Integer productQuantity = Integer.parseInt(mProductQuantity.getText().toString().trim());
                    productQuantity--;
                    if (productQuantity < 0) {
                        showToast(R.string.quantity_validation);
                    } else {
                        ContentValues values = new ContentValues();
                        values.put(COLUMN_PRODUCT_QUANTITY, productQuantity);
                        if (getContentResolver().update(mUri, values, null, null) == 0) {
                            showToast(R.string.update_error);
                        } else {
                            showToast(R.string.update_success);
                        }
                    }
                });

        findViewById(R.id.delete_button)
                .setOnClickListener(v -> {
                    if (getContentResolver().delete(mUri, null, null) == 0) {
                        showToast(R.string.delete_error);
                    } else {
                        showToast(R.string.delete_success);
                    }
                    startActivity(new Intent(DetailsActivity.this, MainActivity.class));
                });

        findViewById(R.id.call_button)
                .setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + String.valueOf(mSupplierPhone.getText()).trim()))));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new CursorLoader(this, mUri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        int idColumnProductName = cursor.getColumnIndex(COLUMN_PRODUCT_NAME);
        int idColumnProductPrice = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE);
        int idColumnProductQuantity = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY);
        int idColumnSupplierName = cursor.getColumnIndex(COLUMN_SUPPLIER_NAME);
        int idColumnSupplierPhone = cursor.getColumnIndex(COLUMN_SUPPLIER_PHONE);

        if (cursor.moveToFirst()) {
            String productName = cursor.getString(idColumnProductName);
            double price = cursor.getDouble(idColumnProductPrice);
            int quantity = cursor.getInt(idColumnProductQuantity);
            String supplierName = cursor.getString(idColumnSupplierName);
            String supplierPhone = cursor.getString(idColumnSupplierPhone);

            mProductName.setText(productName);
            mProductPrice.setText(Double.toString(price));
            mProductQuantity.setText(Integer.toString(quantity));
            mSupplierName.setText(supplierName);
            mSupplierPhone.setText(supplierPhone);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mProductName.setText("");
        mProductPrice.setText("0");
        mProductQuantity.setText("0");
        mSupplierName.setText("");
        mSupplierPhone.setText("");
    }

    private void showToast(int msg) {

        Toast.makeText(DetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
