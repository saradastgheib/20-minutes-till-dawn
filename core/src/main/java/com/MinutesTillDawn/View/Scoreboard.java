package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.ScoreController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.Enums.ScoreEntry;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Scoreboard implements Screen {
    Stage stage;
    ScoreController manager = ScoreController.getController();
    Skin skin = GameAssetManager.getGameAssetManager().getSkin();
    String sortBy = "survivaltime";
    @Override
    public void show() {

        try {
            stage = new Stage();

            Table table = new Table();
            table.setFillParent(true);
            stage.addActor(table);

            Array<ScoreEntry> sortedScores = manager.getScoresSortedBy(sortBy);

            Label title = new Label("Top 10 Players", skin);
            title.setFontScale(2f);
            System.out.println("meow");
            table.add(title).colspan(4).padBottom(20).row();
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
                if (i == 0) nameLabel.setColor(Color.GOLD);
                else if (i == 1) nameLabel.setColor(new Color(192f / 255f, 192f / 255f, 192f / 25f, 1));
                else if (i == 2) nameLabel.setColor(new Color(205f / 255f, 127f / 25f, 50f / 255f, 1));

                table.add(nameLabel);
                table.add(new Label(String.valueOf(entry.score) ,skin));
                table.add(new Label(String.valueOf(entry.kills), skin));
                table.add(new Label(String.valueOf(entry.survivalTime), skin));
                table.row();
            }
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
}
