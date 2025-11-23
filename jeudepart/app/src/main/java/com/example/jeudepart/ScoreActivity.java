package com.example.jeudepart;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ScoreActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Match[] meilleurMatch;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        databaseHelper = new DatabaseHelper(this);
        List<Match> matchList = databaseHelper.readMatch();
        List<User> users = databaseHelper.readUsers();

        getBestMatchUser(users, matchList);
        setupListView();
        setupBoutonRetour();
    }

    private void setupBoutonRetour() {
        Button retour = findViewById(R.id.btnRetourScoreboard);
        retour.setOnClickListener((e) -> {
            onBackPressed();
        });
    }

    private void setupListView() {
        ListView scoreboard = findViewById(R.id.listeScores);
        ScoreboardAdapter adapter = new ScoreboardAdapter(this, meilleurMatch);
        scoreboard.setAdapter(adapter);
    }

    private void getBestMatchUser(List<User> users, List<Match> matchList) {
        List<Match> matches = new ArrayList<>();

        for (User u : users){
            Optional<Match> userMatch = matchList.stream().filter(m -> m.getUser().getUser_id() == u.getUser_id())
                    .min(Comparator.comparingInt(Match::getScore));
            userMatch.ifPresent(matches::add);
        }

        matches.sort(new Comparator<Match>() {
            @Override
            public int compare(Match match1, Match match2) {
                return Integer.compare(match1.getScore(), match2.getScore());
            }
        });

        meilleurMatch = matches.toArray(new Match[]{});
    }
}
