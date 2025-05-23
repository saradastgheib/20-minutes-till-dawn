package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.GameController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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
        switch (i) {
            case Input.Keys.L:
                controller.getPlayerController().getPlayer().upgradeLevel();
                break;
            case Input.Keys.R:
                controller.timeRemaining -= 60;
                break;
            case Input.Keys.H:
                Player player = controller.getPlayerController().getPlayer();
                if (player.getHealthPoints() == 1) {
                    player.adjustHP(1);
                }
                break;
            case Input.Keys.B:
                //bossFight();
                break;
            case Input.Keys.F:
                //freezeEnemies();
                break;
            case Input.Keys.P:
                Main.getMain().setScreen(new PauseMenu(this));
                break;
        }
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
        try {
            stage = new Stage(new ScreenViewport(), Main.getBatch());
            Gdx.input.setInputProcessor(this);
        }
        catch (Exception e) {
            System.out.println("2" + e.getMessage());
        }
    }

    @Override
    public void render(float v) {

        try {
            Main.getBatch().setColor(1, 1, 1, 1);
            Batch batch = Main.getBatch();
            if (GameAssetManager.getGameAssetManager().bwEnabled) {
                batch.setShader(Main.getMain().grayscaleShader);
            } else {
                batch.setShader(null);
            }
            if (controller.timeRemaining > 0) {
                controller.timeRemaining -= v;
            } else {
                controller.timeRemaining = 0;
                //endGame()
            }
            controller.updateGame();
            Main.getBatch().begin();

            controller.renderGame();

            Main.getBatch().end();
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

    public GameController getController() {
        return controller;
    }
}
