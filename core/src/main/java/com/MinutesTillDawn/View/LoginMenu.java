package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.LoginMenuController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoginMenu implements Screen {
    private static LoginMenu loginMenu;
    public Stage stage;
    public TextField usernameField, passwordField;
    public TextButton loginButton, forgotPasswordButton, registerButton;
    public Label error;
    public LoginMenuController controller;
    public Skin skin;

    public LoginMenu(LoginMenuController controller, Skin skin) {
        try {
            loginMenu = this;
            this.skin = skin;
            this.controller = controller;
            this.loginButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.LOGINMENU.getText(), skin);
            this.usernameField = new TextField("", skin);
            this.passwordField = new TextField("", skin);
            this.forgotPasswordButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.FORGOTYOURPASSWORD.getText(), skin);
            this.registerButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.REISTERMENU.getText(), skin);
            this.error = new Label("", skin);
            controller.setView(this);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static LoginMenu getLoginMenu() {
        if (loginMenu != null) return  loginMenu;
        return new LoginMenu(LoginMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin());
    }
    @Override
    public void show() {
        try {
            stage = new Stage(new ScreenViewport(), Main.getBatch());
            Gdx.input.setInputProcessor(stage);

            Table table = new Table();
            table.setFillParent(true);
            table.center();
            stage.addActor(table);
            Label title = new Label(com.MinutesTillDawn.Model.Enums.Label.LOGINLABEL.getText(), skin);
            title.setFontScale(1.2f);
            TextField.TextFieldStyle style = usernameField.getStyle();

            style.fontColor = Color.WHITE;
            style.disabledFontColor = Color.GRAY;

            usernameField.setStyle(style);
            Drawable background = skin.newDrawable("white", new Color(0.1f, 0.1f, 0.2f, 1f));
            style.background = background;

            usernameField.setMessageText(com.MinutesTillDawn.Model.Enums.Label.USERNAMEFIELDPLACEHOLDER.getText());
            passwordField.setMessageText(com.MinutesTillDawn.Model.Enums.Label.PASSWORDFIELDPLACEHOLDER.getText());
            passwordField.setPasswordMode(true);
            passwordField.setPasswordCharacter('*');
            error.setColor(Color.RED);
            setButton(loginButton);
            setButton(registerButton);
            setButton(forgotPasswordButton);
            table.add(title).colspan(2).padBottom(20).padBottom(15).row();
            Label label = new Label(com.MinutesTillDawn.Model.Enums.Label.USERNAMEFIELD.getText(), skin);
            label.setColor(new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f));
            table.add(label).right().padRight(10).padBottom(15);
            table.add(usernameField).width(200).padBottom(15).row();

            Label label1 = new Label(com.MinutesTillDawn.Model.Enums.Label.PASSWORDFIELD.getText(), skin);
            label1.setColor(new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f));
            table.add(label1).right().padRight(10).padBottom(15);
            table.add(passwordField).width(200).padBottom(15).row();
            table.add(loginButton).padTop(10).padBottom(15);
            table.add(registerButton).padTop(10).padBottom(15).row();
            table.add(forgotPasswordButton).colspan(2).padTop(10).padBottom(15).row();
            table.add(error).colspan(2).padTop(10);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void render(float v) {
        try {
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
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
