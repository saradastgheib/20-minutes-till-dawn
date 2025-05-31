package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.ScoreController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.ScoreEntry;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.UserDatabase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Scoreboard implements Screen {
    Stage stage;
    ScoreController manager;
    Skin skin = GameAssetManager.getGameAssetManager().getSkin();

    public TextButton sortByScore, sortByKills, sortByTime, sortByName;

    public Scoreboard() {
        manager = ScoreController.getController();
         sortByScore = new TextButton("Sort by Score", skin);
         sortByKills = new TextButton("Sort by Kills", skin);
         sortByTime = new TextButton("Sort by Time", skin);
         sortByName = new TextButton("Sort by Username", skin);
         manager.setView(this);
    }
    @Override
    public void show() {

        try {
            stage = new Stage();

            Gdx.input.setInputProcessor(stage);
            Table table = new Table();
            table.setFillParent(true);
            stage.addActor(table);

            Array<ScoreEntry> sortedScores = manager.getScoresSortedBy(manager.sortBy);

            Label title = new Label("Top 10 Players", skin);
            title.setFontScale(2f);
            title.setColor(92f / 255f, 116f/255f, 92f/255f, 1);
            System.out.println("meow");
            table.add(title).colspan(4).padBottom(50).row();
            System.out.println("meow1");
            // Header
            table.add(new Label("Username" , skin)).pad(10);
            table.add(new Label("Score", skin)).pad(10);
            table.add(new Label("Kills", skin)).pad(10);
            table.add(new Label("Survival Time", skin)).pad(10);
            table.row();

            for (int i = 0; i < Math.min(10, sortedScores.size); i++) {
                ScoreEntry entry = sortedScores.get(i);
                Label nameLabel = new Label(entry.username, skin);
                if (i == 0 || i == 1 || i == 2) {
                    nameLabel.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
                }

                if (entry.username.equals(UserDatabase.getDatabase().getCurrentUser().getUsername())) {
                    nameLabel.setColor(92f / 255f, 116f/25f, 92f/255f, 1);
                }
                table.add(nameLabel);
                table.add(new Label(String.valueOf(entry.score) ,skin));
                table.add(new Label(String.valueOf(entry.kills), skin));
                table.add(new Label(String.valueOf(entry.survivalTime), skin));
                table.row();


            }

            setButton(sortByScore);
            setButton(sortByKills);
            setButton(sortByTime);
            setButton(sortByName);
            table.add(sortByScore).padTop(50).padRight(20);
            table.add(sortByKills).padTop(50).padRight(20);
            table.add(sortByTime).padTop(50).padRight(20);
            table.add(sortByName).padTop(50).padRight(20);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(new Color(13f / 255f, 18f / 255f, 37f / 255f, 1f));

        Batch batch = Main.getBatch();
        if (GameAssetManager.getGameAssetManager().bwEnabled) {
            ScreenUtils.clear(Color.BLACK);
            batch.setShader(Main.getMain().grayscaleShader);
        } else {
            batch.setShader(null);
        }

        Main.getBatch().begin();
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    private void setButton(TextButton button) {
        button.setColor(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        button.getStyle().fontColor = Color.WHITE;
        button.getStyle().overFontColor= new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
    }
}
