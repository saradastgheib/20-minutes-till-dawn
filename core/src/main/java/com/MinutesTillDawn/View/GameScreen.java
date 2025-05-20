package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.GameController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

public class GameScreen  implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    Random random = new Random();

    public GameScreen(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

    }
    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    @Override
    public void show() {
        try {System.out.println("GameScreen show called");
            System.out.println("playerController: " + controller.getPlayerController());
            System.out.println("worldController: " + controller.getWorldController());

            stage = new Stage(new ScreenViewport());
            Gdx.input.setInputProcessor(this);
        }
        catch (Exception e) {
            System.out.println("2" + e.getMessage());
        }
    }

    @Override
    public void render(float v) {

        Main.getBatch().setColor(1, 1, 1, 1);
        if (controller.timeRemaining > 0 ) {
            controller.timeRemaining -= v;
        }
        else {
            controller.timeRemaining = 0;
            //endGame()
        }
        controller.updateGame();
        Main.getBatch().begin();

        controller.renderGame();

        Main.getBatch().end();
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
