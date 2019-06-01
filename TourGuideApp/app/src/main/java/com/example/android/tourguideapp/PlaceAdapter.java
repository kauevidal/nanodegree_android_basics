package com.example.android.tourguideapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tourguideapp.model.Place;

import java.util.List;

public class PlaceAdapter extends ArrayAdapter<Place> {

    PlaceAdapter(@NonNull Context context, int resource, @NonNull List<Place> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        View listItemView = view;
        if (view == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.place, parent, false);
        }

        Place currentPlace = getItem(position);

        if (currentPlace != null) {
            ImageView imageView = listItemView.findViewById(R.id.image);

            if (currentPlace.getImageId() != -1) {
                imageView.setImageResource(currentPlace.getImageId());
            } else {
                imageView.setVisibility(View.GONE);
            }

            TextView placeName = listItemView.findViewById(R.id.place_name_text_view);
            placeName.setText(String.valueOf(currentPlace.getName()));

            TextView placeDescription = listItemView.findViewById(R.id.place_description_text_view);
            placeDescription.setText(String.valueOf(currentPlace.getDescription()));
        }

        return listItemView;
    }
}
