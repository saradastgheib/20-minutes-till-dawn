package com.MinutesTillDawn;

import com.MinutesTillDawn.Controller.LoginMenuController;
import com.MinutesTillDawn.Controller.MainMenuController;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.MyGame;
import com.MinutesTillDawn.View.ForgotPasswordMenu;
import com.MinutesTillDawn.View.LoginMenu;
import com.MinutesTillDawn.View.MainMenu;
import com.MinutesTillDawn.View.RegisterMenu;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    public ShaderProgram grayscaleShader;
    public static MyGame game;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        ShaderProgram.pedantic = false;

        grayscaleShader = new ShaderProgram(
            Gdx.files.internal("shaders/default.vert"),
            Gdx.files.internal("shaders/grayscale.frag")
        );

        if (!grayscaleShader.isCompiled()) {
            System.err.println("Shader compile error: " + grayscaleShader.getLog());
        }
        main.setScreen(MainMenu.getMainMenu());

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        GameAssetManager.getGameAssetManager().dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }
}
