package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.ChangeMenuController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.Enums.Label;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ChangeMenu implements Screen {

    private Stage stage;
    private static ChangeMenu changeMenu;
    public TextButton settings, profile, preGame, scoreBoard, talent ,back;
    private ChangeMenuController controller;
    private Table table;

    public ChangeMenu(ChangeMenuController controller, Skin skin) {
        this.controller = controller;
        settings = new TextButton(Label.SETTINGS.getText(), skin);
        profile = new TextButton(Label.PROFILE.getText(), skin);
        preGame = new TextButton(Label.PREGAMEMENU.getText(), skin);
        scoreBoard = new TextButton(Label.SCOREBOARD.getText(), skin);
        talent = new TextButton(Label.TALENTMENU.getText(), skin);
        back = new TextButton(Label.BACK.getText(), skin);
        controller.setView(this);
    }


    public static ChangeMenu getChangeMenu() {
        if (changeMenu == null) changeMenu = new ChangeMenu(ChangeMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin());
        return changeMenu;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport(), Main.getBatch());
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();
        setButton(settings);
        setButton(profile);
        setButton(preGame);
        setButton(scoreBoard);
        setButton(talent);
        setButton(back);
        table.add(settings).padBottom(15);
        table.row();
        table.add(profile).padBottom(15);
        table.row();
        table.add(preGame).padBottom(15);
        table.row();
        table.add(scoreBoard).padBottom(15);
        table.row();
        table.add(talent).padBottom(15);
        table.row();
        table.add(back).padBottom(15);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(new Color(13f/255f,18f/255f,37f/255f,255f/255f));

        Batch batch = Main.getBatch();
        if (GameAssetManager.getGameAssetManager().bwEnabled) {
            ScreenUtils.clear(Color.BLACK);
            batch.setShader(Main.getMain().grayscaleShader);
        } else {
            batch.setShader(null);
        }

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

    private void setButton(TextButton button) {
        button.setColor(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        button.getStyle().fontColor = Color.WHITE;
        button.getStyle().overFontColor= new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
    }
}
