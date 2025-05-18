package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.RegisterMenuController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RegisterMenu implements Screen {
    private static RegisterMenu registerMenu;
    private RegisterMenuController controller;
    private Skin skin;
    public TextField usernameField, passwordField, securityQuestionField;
    public TextButton registerButton, guestButton, backButton;
    public Label message;
    public Stage stage;
    public SelectBox<String> securityQuestionBox;


    public RegisterMenu(RegisterMenuController controller, Skin skin) {
        registerMenu = this;
        this.controller = controller;
        this.skin = skin;
        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        securityQuestionField = new TextField("", skin);
        registerButton = new TextButton("Register", skin);
        guestButton = new TextButton("Play as guest", skin);
        backButton = new TextButton("Back", skin);
        message = new Label("", skin);
        registerMenu = this;
        controller.setView(this);
    }
    public static RegisterMenu getRegisterMenu() {
        if (registerMenu != null)
            return registerMenu;
        return new RegisterMenu(RegisterMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin());
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        Label title = new Label("Register Menu", skin);
        title.setFontScale(1.2f);

        usernameField.setMessageText("Enter your username");
        passwordField.setMessageText("Enter your password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        setButton(registerButton);
        setButton(guestButton);
        setButton(backButton);
        securityQuestionBox = new SelectBox<>(skin);
        securityQuestionBox.setItems(
            "What was the name of your first pet?",
            "What street did you grow up on?",
            "What is your favorite food?",
            "What is your elementary school's name?"
        );

        securityQuestionField.setMessageText("Answer to security question");

        message.setWrap(true);
        message.setAlignment(Align.center);

        // Layout
        table.add(title).colspan(2).padBottom(10);
        table.row();

        table.add(usernameField).colspan(2).width(400).padBottom(10);
        table.row();

        table.add(passwordField).colspan(2).width(400).padBottom(10);
        table.row();

        table.add(securityQuestionBox).colspan(2).width(400).padBottom(10);
        table.row();

        table.add(securityQuestionField).colspan(2).width(400).padBottom(25);
        table.row();

        table.add(registerButton).width(300).padRight(50);
        table.add(guestButton).width(300);
        table.row();

        table.add(backButton).colspan(2).width(200).padTop(10);
        table.row();

        table.add(message).colspan(2).width(500).padTop(15);
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

    private void setButton(TextButton button) {
        button.setColor(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        button.getStyle().fontColor = Color.WHITE;
        button.getStyle().overFontColor= new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
    }
}
