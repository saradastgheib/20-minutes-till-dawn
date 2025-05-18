package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.MainMenuController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.UserDatabase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu implements Screen {
    private Skin skin;
    private static MainMenu mainMenu;
    private Stage stage;
    public TextButton changeMenuButton, playSavedGame, logoutButton;
    public Image avatarImage;
    public Label username;
    public Table table;
    private final MainMenuController controller;
    public MainMenu(MainMenuController controller, Skin skin) {
        this.controller = controller;
        changeMenuButton = new TextButton("Change menu", skin);
        playSavedGame = new TextButton("Resume last saved game", skin);
        logoutButton = new TextButton("Logout" ,skin);
        this.skin = skin;
        controller.setView(this);
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();
        Texture avatarTexture = new Texture(Gdx.files.internal(UserDatabase.getDatabase().getCurrentUser().getAvatarPath()));
        avatarImage = new Image(avatarTexture);
        avatarImage.setScaling(Scaling.fit);
        username = new Label(UserDatabase.getDatabase().getCurrentUser().getUsername() + "\t" + UserDatabase.getDatabase().getCurrentUser().getTotalPoints(), skin);
        changeMenuButton.setColor(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        changeMenuButton.getStyle().fontColor = Color.WHITE;
        changeMenuButton.getStyle().overFontColor= new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        playSavedGame.setColor(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        playSavedGame.getStyle().overFontColor =  new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        playSavedGame.getStyle().fontColor = Color.WHITE;
        logoutButton.setColor(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        logoutButton.getStyle().overFontColor =  new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        logoutButton.getStyle().fontColor = Color.WHITE;
        table.add(avatarImage).size(384, 384).padTop(10).padBottom(15);
        table.row();
        username.setColor(new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f));
        username.setFontScale(1.3f);
        table.add(username).padBottom(15);
        table.row().pad(10, 0, 10, 0);
        table.add(changeMenuButton).width(640);
        table.row().pad(10, 0, 10, 0);
        table.add(playSavedGame);
        table.row().pad(10, 0, 10, 0);
        table.add(logoutButton);

        stage.addActor(table);
    }

    public static MainMenu getMainMenu() {
        if (mainMenu == null) mainMenu = new MainMenu(MainMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin());
        return mainMenu;
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        Batch batch = Main.getBatch();
        if (GameAssetManager.getGameAssetManager().bwEnabled) {
            batch.setShader(Main.getMain().grayscaleShader);
        } else {
            batch.setShader(null); // normal rendering
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
}
