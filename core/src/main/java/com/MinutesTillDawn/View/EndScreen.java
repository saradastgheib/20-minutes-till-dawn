package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.Player;
import com.MinutesTillDawn.Model.UserDatabase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndScreen implements Screen {

    private Stage stage;
    private boolean won;
    private Player player;
    private float timeSurvived;

    public EndScreen(boolean won, Player player, float timeSurvived) {
        this.won = won;
        this.player = player;
        this.timeSurvived = timeSurvived;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport(), Main.getBatch());
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCursorCatched(false);
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();

        Label winStatus = new Label("", skin);
        winStatus.getStyle().font = GameAssetManager.getGameAssetManager().getCustomFont();
        if (won) winStatus.setText(com.MinutesTillDawn.Model.Enums.Label.WIN.getText());
        else winStatus.setText(com.MinutesTillDawn.Model.Enums.Label.DEAD.getText());
        winStatus.setFontScale(6f);
        table.add(winStatus).padBottom(50);
        table.row();

        int survivalMins = (int) (timeSurvived/60);
        int survivalSecs = (int) (timeSurvived % 60);
        Label survivalTime = new Label(com.MinutesTillDawn.Model.Enums.Label.SURVIVALTIME.getText() + "  " + survivalMins + " : " + survivalSecs, skin);
        survivalTime.setFontScale(1.5f);
        survivalTime.setColor(92f / 255f, 116f/255f, 92f/255f, 1);
        table.add(survivalTime);
        table.row();
        Label username = new Label(player.getUsername(), skin);
        username.setFontScale(1.5f);
        username.setColor(92f / 255f, 116f/255f, 92f/255f, 1);
        table.add(username);
        table.row();
        int kills = player.kills;
        Label killsCount = new Label(com.MinutesTillDawn.Model.Enums.Label.KILLSCOUNT.getText() + kills, skin);
        killsCount.setFontScale(1.5f);
        killsCount.setColor(92f / 255f, 116f/255f, 92f/255f, 1);
        table.add(killsCount);
        table.row();

        int score = (int) (player.kills * timeSurvived);
        Label scoreLabel = new Label("score : " + score, skin);
        scoreLabel.setFontScale(1.5f);
        scoreLabel.setColor(92f / 255f, 116f/255f, 92f/255f, 1);
        table.add(scoreLabel);
    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(new Color(13f / 255f, 18f / 255f, 37f / 255f, 1f));

        Batch batch = Main.getBatch();
        if (GameAssetManager.getGameAssetManager().bwEnabled) {
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
