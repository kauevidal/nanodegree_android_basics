package com.example.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static data.ProductContract.ProductEntry;
import static data.ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY;

public class ProductCursorAdapter extends CursorAdapter {

    private Context mContext;

    ProductCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.product, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        TextView productNameView = view.findViewById(R.id.product_name);
        TextView productPriceView = view.findViewById(R.id.product_price);
        TextView productQuantityView = view.findViewById(R.id.product_quantity);

        int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
        int productNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        int productPriceIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
        int productQuantityIndex = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY);

        Long id = Long.parseLong(cursor.getString(idColumnIndex));
        String productName = cursor.getString(productNameColumnIndex);
        Double productPrice = cursor.getDouble(productPriceIndex);
        String productQuantity = cursor.getString(productQuantityIndex);

        productNameView.setText(context.getString(R.string.product_name).concat(" ").concat(productName));
        productPriceView.setText(context.getString(R.string.price).concat(" ").concat(String.valueOf(productPrice)));
        productQuantityView.setText(context.getString(R.string.quantity).concat(" ").concat(productQuantity));

        view.findViewById(R.id.details_button)
                .setOnClickListener(v -> {
                    Uri currentProduct = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, id);
                    context.startActivity(new Intent(context, DetailsActivity.class).setData(currentProduct));
                });

        view.findViewById(R.id.sell_button)
                .setOnClickListener(v -> {
                    Integer quantity = Integer.parseInt(productQuantity.trim());
                    quantity--;
                    if (quantity < 0) {
                        showToast(R.string.quantity_validation);
                        return;
                    }
                    Uri currentProduct = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, id);
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_PRODUCT_QUANTITY, quantity);
                    update(currentProduct, values);
                });
    }

    private void update(Uri currentProduct, ContentValues values) {

        if (mContext.getContentResolver().update(currentProduct, values, null, null) != 0) {
            showToast(R.string.update_success);
        } else {
            showToast(R.string.update_error);
        }
    }

    private void showToast(int msg) {

        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
