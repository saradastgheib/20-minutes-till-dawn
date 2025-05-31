package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.GameController;
import com.MinutesTillDawn.Controller.ScoreController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.*;
import com.MinutesTillDawn.Model.Enums.EnemyType;
import com.MinutesTillDawn.Model.ScoreEntry;
import com.MinutesTillDawn.Model.saveStuff.EnemyData;
import com.MinutesTillDawn.Model.saveStuff.SaveData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

public class GameScreen  implements Screen, InputProcessor {
    private Stage stage;
    private final GameController controller;
    private final Array<Bullet> bullets = new Array<>();
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();


    Random random = new Random();

    public GameScreen(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

    }

    public GameScreen(SaveData data) {

        controller = new GameController();
        controller.setView(this);
        Player player = controller.getPlayerController().getPlayer();
        player.setPosition(data.playerX, data.playerY);
        player.setHealth(data.playerHP);
        player.setKills(data.kills);
        controller.timeRemaining = data.timeRemaining;

        for (EnemyData e : data.enemies) {
            Enemy enemy = new Enemy(EnemyType.valueOf(e.type.toUpperCase()));
            enemy.setPosition(e.x, e.y);
            enemy.setHealth(e.hp);
            controller.enemies.add(enemy);
        }

    }
    @Override
    public boolean keyDown(int i) {
        switch (i) {
            case Input.Keys.L:
                controller.getPlayerController().getPlayer().upgradeLevel();
                break;
            case Input.Keys.T:
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
            case Input.Keys.SPACE:
                controller.aimAutoEnabled = !controller.aimAutoEnabled;
                break;
            case Input.Keys.R:
                player = controller.getPlayerController().getPlayer();
                player.getWeapon().reload();
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
        if (i3 == Input.Buttons.LEFT) {
            Vector3 mouse = new Vector3(i, i1, 0);
            stage.getViewport().unproject(mouse);

            Player player = controller.getPlayerController().getPlayer();
            Sprite playerSprite = player.getPlayerSprite();
            float screenCenterX = Gdx.graphics.getWidth() / 2f;
            float screenCenterY = Gdx.graphics.getHeight() / 2f;
            float gunTipX = screenCenterX + 90f;
            float gunTipY = screenCenterY + 90f;
            Vector2 bulletStartPos = new Vector2(gunTipX, gunTipY);
            Vector2 bulletTargetPos = new Vector2(controller.virtualMousePos.x, controller.virtualMousePos.y);
            System.out.println("hello");
            if (player.getWeapon().canShoot()){
                System.out.println("meow");
                bullets.add(new Bullet(bulletStartPos, bulletTargetPos));
                player.getWeapon().shoot();
            }
        }
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
            Player player = controller.getPlayerController().getPlayer();
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

                ScoreController.getController().addScore(new ScoreEntry(player.getUsername(), player.getTotalPoints(), player.kills, GameSettings.gameTime));
                Main.getMain().setScreen(MainMenu.getMainMenu());
                //endGame(true)
            }
            if (player.getHealthPoints() <= 0) {
                //endGame(false);
                ScoreController.getController().addScore(new ScoreEntry(player.getUsername(), player.getTotalPoints(), player.kills, GameSettings.gameTime * 60f - controller.timeRemaining));

            }

            controller.updateGame(v);
            Main.getBatch().begin();
            player.updateAbilities(v);
            player.getWeapon().update(v);
            controller.renderGame();

            Main.getBatch().end();
            stage.draw();
            for (Bullet b : bullets) {
                b.update(v);
            }

// Draw bullets
            shapeRenderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for (Bullet b : bullets) {
                b.draw(shapeRenderer);
            }
            shapeRenderer.end();
        }catch (Exception e) {
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

    public Stage getStage() {
        return stage;
    }
}
