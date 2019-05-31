package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView musicsButton = findViewById(R.id.music_library_button);
        TextView artistsButton = findViewById(R.id.artist_library_button);

        musicsButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MusicActivity.class);
            startActivity(intent);
        });

        artistsButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ArtistActivity.class);
            startActivity(intent);
        });
    }
}
