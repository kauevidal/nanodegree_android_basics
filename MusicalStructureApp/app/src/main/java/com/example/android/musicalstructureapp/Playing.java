package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Playing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        Bundle bundle = getIntent().getExtras();
        final Button buttonStop = findViewById(R.id.stopButton);
        final Button buttonHome = findViewById(R.id.homeButton);


        if (bundle != null) {
            buttonStop.setVisibility(View.VISIBLE);
            String music = bundle.getString(getString(R.string.music_name_param));
            TextView musicName = findViewById(R.id.music_name);
            musicName.setText(getString(R.string.you_are_listening_label, music));

        } else {
            showError(R.string.app_error);
        }

        buttonStop.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MusicActivity.class);
            startActivity(intent);
        });

        buttonHome.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            v.getContext().startActivity(intent);
        });
    }

    private void showError(int msgId) {
        Toast.makeText(getApplicationContext(), getString(msgId), Toast.LENGTH_SHORT).show();
    }
}
