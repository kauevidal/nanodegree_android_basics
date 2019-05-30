package com.example.android.scorekeeperapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final Team mTeamA = new Team("Time A", 0, 0);
    private final Team mTeamB = new Team("Time B", 0, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nameA = findViewById(R.id.text_team_a_name);
        nameA.setText(mTeamA.getName());

        TextView nameB = findViewById(R.id.text_team_b_name);
        nameB.setText(mTeamB.getName());

        Button buttonPointA = findViewById(R.id.button_point_team_a);
        buttonPointA.setOnClickListener(v -> addPoint(mTeamA));

        Button buttonSetA = findViewById(R.id.button_set_team_a);
        buttonSetA.setOnClickListener(v -> addSet(mTeamA));

        Button buttonPointB = findViewById(R.id.button_point_team_b);
        buttonPointB.setOnClickListener(v -> addPoint(mTeamB));

        Button buttonSetB = findViewById(R.id.button_set_team_b);
        buttonSetB.setOnClickListener(v -> addSet(mTeamB));

        Button buttonReset = findViewById(R.id.reset_button);
        buttonReset.setOnClickListener(v -> resetScores());
    }

    private void addSet(Team team) {

        team.addSet();
        updateView();
    }

    public void addPoint(final Team team) {

        team.addPoint();
        updateLastTeam(team.getName());
        updateView();
    }

    public void resetScores() {

        mTeamA.setPoint(0);
        mTeamA.setSet(0);
        mTeamB.setPoint(0);
        mTeamB.setSet(0);
        updateView();
        updateLastTeam(getString(R.string.default_last_point_value));
    }

    private void updateView() {

        TextView pointTextViewA = findViewById(R.id.text_point_team_a);
        pointTextViewA.setText(String.valueOf(mTeamA.getPoint()));

        TextView setTextViewA = findViewById(R.id.text_set_team_a);
        setTextViewA.setText(String.valueOf(mTeamA.getSet()));

        TextView scoreTextView = findViewById(R.id.text_point_team_b);
        scoreTextView.setText(String.valueOf(mTeamB.getPoint()));

        TextView setTextView = findViewById(R.id.text_set_team_b);
        setTextView.setText(String.valueOf(mTeamB.getSet()));
    }

    private void updateLastTeam(final String team) {

        TextView teamTextView = findViewById(R.id.currentTeam);
        teamTextView.setText(team);
    }
}
