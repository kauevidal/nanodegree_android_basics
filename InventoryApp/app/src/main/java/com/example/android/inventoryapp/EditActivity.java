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
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import static data.ProductContract.ProductEntry.COLUMN_PRODUCT_NAME;
import static data.ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE;
import static data.ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY;
import static data.ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME;
import static data.ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE;
import static data.ProductContract.ProductEntry.CONTENT_URI;

public class EditActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EDIT_LOADER = 0;

    private Uri mUri;
    private EditText mProductName;
    private EditText mProductPrice;
    private EditText mProductQuantity;
    private EditText mSupplierName;
    private EditText mSupplierPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mUri = getIntent().getData();
        updateViewTitle();

        findViewById(R.id.save_button)
                .setOnClickListener(v -> {
                    if (saveOrUpdateProduct()) {
                        startActivity(new Intent(EditActivity.this, MainActivity.class));
                    }
                });

        findViewById(R.id.cancel_button)
                .setOnClickListener(view -> startActivity(new Intent(EditActivity.this, MainActivity.class)));

        mProductName = findViewById(R.id.edit_product_name);
        mProductPrice = findViewById(R.id.edit_product_price);
        mProductQuantity = findViewById(R.id.edit_product_quantity);
        mSupplierName = findViewById(R.id.edit_supplier_name);
        mSupplierPhone = findViewById(R.id.edit_supplier_number);
    }

    private void updateViewTitle() {

        if (mUri == null) {
            setTitle(getString(R.string.add_title));
        } else {
            setTitle(getString(R.string.edit_title));
            getLoaderManager().initLoader(EDIT_LOADER, null, this);
        }
    }

    private Boolean saveOrUpdateProduct() {

        String productName = String.valueOf(mProductName.getText()).trim();
        String productPrice = String.valueOf(mProductPrice.getText()).trim();
        String productQuantity = String.valueOf(mProductQuantity.getText()).trim();
        String supplierName = String.valueOf(mSupplierName.getText()).trim();
        String supplierPhone = String.valueOf(mSupplierPhone.getText()).trim();

        validateFields(productName, productPrice, productQuantity, supplierName, supplierPhone);

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, productName);
        values.put(COLUMN_PRODUCT_PRICE, Double.parseDouble(productPrice));
        values.put(COLUMN_PRODUCT_QUANTITY, Integer.parseInt(productQuantity));
        values.put(COLUMN_SUPPLIER_NAME, supplierName);
        values.put(COLUMN_SUPPLIER_PHONE, supplierPhone);

        if (mUri == null) {
            return create(values);
        }
        return update(values);
    }

    private boolean create(ContentValues values) {

        if (getContentResolver().insert(CONTENT_URI, values) != null) {
            showToast(R.string.create_success);
            return true;
        }
        showToast(R.string.create_error);
        return false;
    }

    private boolean update(ContentValues values) {

        if (getContentResolver().update(mUri, values, null, null) != 0) {
            showToast(R.string.update_success);
            return true;
        }
        showToast(R.string.update_error);
        return false;
    }

    private void validateFields(String productName, String productPrice, String productQuantity,
                                String supplierName, String supplierPhone) {

        if (TextUtils.isEmpty(productName)) {
            throw new IllegalArgumentException(getString(R.string.required_name));
        }

        if (TextUtils.isEmpty(productPrice) || Double.parseDouble(productPrice) < 0) {
            throw new IllegalArgumentException(getString(R.string.required_price));
        }

        if (TextUtils.isEmpty(productQuantity) || Integer.parseInt(productQuantity) < 0) {
            throw new IllegalArgumentException(getString(R.string.required_quantity));
        }

        if (TextUtils.isEmpty(supplierName)) {
            throw new IllegalArgumentException(getString(R.string.required_supplier_name));
        }

        if (TextUtils.isEmpty(supplierPhone)) {
            throw new IllegalArgumentException(getString(R.string.required_supplier_phone));
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, mUri, null, null, null,
                null);
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
            mProductPrice.setText(String.valueOf(price));
            mProductQuantity.setText(String.valueOf(quantity));
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

        Toast.makeText(EditActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
