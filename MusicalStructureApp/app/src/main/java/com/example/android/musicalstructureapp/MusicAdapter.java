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

import com.example.android.musicalstructureapp.model.Music;

import java.util.ArrayList;

public class MusicAdapter extends ArrayAdapter<Music> {

    public MusicAdapter(Context context, int resource, ArrayList<Music> objects) {
        super(context, resource, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.music_item, parent, false);
        }

        final Music currentMusic = getItem(position);

        if (currentMusic != null) {

            TextView musicNameTextView = listItemView.findViewById(R.id.music_name_text_view);
            musicNameTextView.setText(this.getContext().getString(R.string.playing_music_msg, currentMusic.getName(), currentMusic.getArtist().getName()));

            Button buttonPlay = listItemView.findViewById(R.id.play_button);
            buttonPlay.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), Playing.class);
                Bundle bundle = new Bundle();
                bundle.putString(v.getContext().getString(R.string.music_name_param), currentMusic.getName());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            });
        }

        return listItemView;
    }
}
