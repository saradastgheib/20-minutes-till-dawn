package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.ChangePasswordController;
import com.MinutesTillDawn.Controller.ForgotPasswordController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ChangePassword implements Screen {
    private ChangePasswordController controller;
    private final Skin skin;
    public TextField passwordField;
    public TextButton resetPasswordButton;
    public TextButton backButton;
    public Stage stage;
    public Label error;

    public ChangePassword () {
        skin = GameAssetManager.getGameAssetManager().getSkin();
        controller = new ChangePasswordController();
        this.passwordField = new TextField("", skin);
        this.resetPasswordButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.CHANGEPASSWORD.getText(), skin);
        this.error = new Label("", skin);
        this.backButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.BACK.getText(), skin);
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport(), Main.getBatch());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);
        Label title = new Label(com.MinutesTillDawn.Model.Enums.Label.FORGOTYOURPASSWORD.getText(), skin);
        title.setFontScale(1.2f);

        passwordField.setMessageText(com.MinutesTillDawn.Model.Enums.Label.ENTERYOURPASSWORD.getText());
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        error.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        setButton(resetPasswordButton);
        setButton(backButton);
        table.add(title).colspan(2).padBottom(10);
        table.row();
        table.add(passwordField).colspan(2).width(400).padBottom(25);
        table.row();
        table.add(resetPasswordButton).width(450).padRight(70);
        table.add(backButton).width(450);
        table.row();
        table.add(error).colspan(2).padTop(10);

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
