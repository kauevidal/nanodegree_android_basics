package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicalstructureapp.model.Artist;
import com.example.android.musicalstructureapp.model.MusicDAO;

public class ArtistDetailActivity extends AppCompatActivity {

    private MusicDAO mMusicDAO = new MusicDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_detail);

        Button buttonLibrary = findViewById(R.id.libraryButton);
        buttonLibrary.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MusicActivity.class);
            v.getContext().startActivity(intent);
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            int id = bundle.getInt(getString(R.string.artist_id_param));
            Artist artist = mMusicDAO.getArtist(id);

            if (artist != null) {
                TextView artistName = findViewById(R.id.artist_name);
                artistName.setText(artist.getName());
                TextView artistDescription = findViewById(R.id.artist_description);
                artistDescription.setText(artist.getDescription());
            } else {
                showError(R.string.search_artist_error);
            }
        } else {
            showError(R.string.app_error);
        }
    }

    private void showError(int msgId) {
        Toast.makeText(getApplicationContext(), getString(msgId), Toast.LENGTH_SHORT).show();
    }
}
