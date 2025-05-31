package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.ScoreEntry;
import com.MinutesTillDawn.View.Scoreboard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class ScoreController {
    private static ScoreController controller;
    private final Json json = new Json();
    private final FileHandle file = Gdx.files.local("scoreboard.json");
    private Array<ScoreEntry> scores;
    private Scoreboard view;
    public String sortBy = "survivaltime";

    public void setView(Scoreboard view) {
        this.view = view;
        addListeners();
    }

    public void addListeners() {

        view.sortByScore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sortBy = "score";
                Main.getMain().setScreen(new Scoreboard());
            }
        });
        view.sortByKills.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sortBy = "kills";
                Main.getMain().setScreen(new Scoreboard());
            }
        });

        view.sortByName.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sortBy = "username";
                Main.getMain().setScreen(new Scoreboard());
            }
        });
        view.sortByTime.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sortBy = "survivaltime";
                Main.getMain().setScreen(new Scoreboard());
            }
        });
    }
    public ScoreController() {
        loadScores();
    }

    public static ScoreController getController() {
        if (controller == null) controller = new ScoreController();
        return controller;
    }

    public void loadScores() {
        if (file.exists()) {
            scores = json.fromJson(Array.class, ScoreEntry.class, file);
        } else {
            scores = new Array<>();
        }
    }

    public void saveScores() {
        json.toJson(scores, file);
    }

    public void addScore(ScoreEntry entry) {
        scores.add(entry);
        saveScores();
    }

    public Array<ScoreEntry> getScoresSortedBy(String field) {
        Array<ScoreEntry> sorted = new Array<>(scores);
        sorted.sort((a, b) -> {
            switch (field.toLowerCase()) {
                case "score":
                    return Integer.compare(b.score, a.score);
                case "kills":
                    return Integer.compare(b.kills, a.kills);
                case "survivaltime":
                    return Float.compare(b.survivalTime, a.survivalTime);
                case "username":
                    return a.username.compareToIgnoreCase(b.username);
            }
            return 0;
        });
        return sorted;
    }

    public Array<ScoreEntry> getScores() {
        return scores;
    }
}
