package com.example.android.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.musicalstructureapp.model.Artist;
import com.example.android.musicalstructureapp.model.MusicDAO;

import java.util.ArrayList;

public class ArtistActivity extends AppCompatActivity {

    private MusicDAO mMusicDAO = new MusicDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        ArrayList<Artist> artists = mMusicDAO.getArtists();
        ArtistAdapter artistsAdapter = new ArtistAdapter(this, R.layout.artist_item, artists);
        ListView listView = findViewById(R.id.artistsList);
        listView.setAdapter(artistsAdapter);
    }
}