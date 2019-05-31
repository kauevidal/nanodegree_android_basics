package com.example.android.musicalstructureapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.musicalstructureapp.model.Artist;

import java.util.ArrayList;

public class ArtistAdapter extends ArrayAdapter<Artist> {

    public ArtistAdapter(Context context, int resource, ArrayList<Artist> objects) {
        super(context, resource, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        View listItemView = view;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.artist_item, parent, false);
        }

        final Artist currentArtist = getItem(position);
        TextView artistTextView = listItemView.findViewById(R.id.artist_name_view);
        artistTextView.setText(currentArtist != null ? currentArtist.getName() : "");

        Button buttonDetail = listItemView.findViewById(R.id.artist_detail_button);
        buttonDetail.setOnClickListener(
                v -> {
                    Intent intent = new Intent(v.getContext(), ArtistDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(v.getContext().getString(R.string.artist_id_param), currentArtist != null ? currentArtist.getId() : 0);
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                });

        return listItemView;
    }
}
