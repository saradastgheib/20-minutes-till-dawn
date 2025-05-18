package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.MainMenuController;
import com.MinutesTillDawn.Controller.PreGameMenuController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PreGameMenu implements Screen {
    private static PreGameMenu preGameMenu;
    private Stage stage;
    public TextButton playButton;
    private final Label gameTitle;
    public Table table;
    private final PreGameMenuController controller;
    public PreGameMenu(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        this.playButton = new TextButton("play", skin);
        this.gameTitle = new Label("This is a title", skin);
        this.table = new Table();
    }

    public static PreGameMenu getPreGameMenu() {
        if (preGameMenu == null) preGameMenu = new PreGameMenu(PreGameMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin());
        return preGameMenu;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        table.center();
        table.add(gameTitle);
        table.row().pad(10, 0, 10, 0);
        table.row().pad(10, 0, 10, 0);
        table.add(playButton);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
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
    public TextButton getPlayButton() {
        return playButton;
    }


    private void setButton(TextButton button) {
        button.setColor(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        button.getStyle().fontColor = Color.WHITE;
        button.getStyle().overFontColor= new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
    }
}
