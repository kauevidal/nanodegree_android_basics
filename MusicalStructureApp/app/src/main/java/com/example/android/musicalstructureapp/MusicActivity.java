package com.example.android.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.musicalstructureapp.model.Music;
import com.example.android.musicalstructureapp.model.MusicDAO;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    private MusicDAO mMusicDAO = new MusicDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        ArrayList<Music> musics = mMusicDAO.getMusics();
        MusicAdapter musicsAdapter = new MusicAdapter(this, R.layout.music_item, musics);
        ListView listView = findViewById(R.id.musicsList);
        listView.setAdapter(musicsAdapter);
    }
}
