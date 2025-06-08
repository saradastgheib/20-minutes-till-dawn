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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class GameScreen  implements Screen, InputProcessor {
    private Stage stage;
    private final GameController controller;
    private final Array<Bullet> bullets = new Array<>();
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    Skin skin = GameAssetManager.getGameAssetManager().getSkin();
    Label levelLabel, hpLabel, notifLabel;
    Label killLabel, timeLabel, ammoLabel;
    ProgressBar xpBar;
    public String notification = "";

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
        player.setLevel(data.level);
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
                controller.getPlayerController().getPlayer().upgradeLevel(this);
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
                controller.timeRemaining = GameSettings.gameTime*20f;
                break;
            case Input.Keys.F:
                controller.getEnemyController().frozen = !controller.getEnemyController().frozen;
                System.out.println("frozen : " + controller.getEnemyController().frozen);
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
            controller.getWorldController().getCamera().unproject(mouse);

            Player player = controller.getPlayerController().getPlayer();
            Sprite playerSprite = player.getPlayerSprite();
            float screenCenterX = Gdx.graphics.getWidth() / 2f;
            float screenCenterY = Gdx.graphics.getHeight() / 2f;
            float gunTipX = screenCenterX + 90f;
            float gunTipY = screenCenterY + 90f;
            Vector2 bulletStartPos = new Vector2(gunTipX, gunTipY);
            Sprite cursor = GameAssetManager.getGameAssetManager().getCursor();
            Vector2 bulletTargetPos = new Vector2(controller.virtualMousePos.x + cursor.getWidth()/2, controller.virtualMousePos.y + cursor.getHeight()/2);
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
            Gdx.input.setCursorCatched(true);
            levelLabel = new Label("level: 1", skin);
            levelLabel.getStyle().font = GameAssetManager.getGameAssetManager().getCustomFont();
            levelLabel.setPosition(10, Gdx.graphics.getHeight()- levelLabel.getHeight() - 10);
            levelLabel.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
            levelLabel.setFontScale(1.1f);
            stage.addActor(levelLabel);
            killLabel = new Label("kills: 0", skin);
            killLabel.setPosition(10, Gdx.graphics.getHeight()- levelLabel.getHeight() - 40);
            killLabel.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
            killLabel.setFontScale(1.1f);
            stage.addActor(killLabel);
            int minutes = GameSettings.gameTime;
            timeLabel = new Label(minutes + " : " + "00" , skin);
            timeLabel.setFontScale(1.5f);
            timeLabel.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
            timeLabel.pack();
            notifLabel = new Label(notification, skin);
            notifLabel.getStyle().font = GameAssetManager.getGameAssetManager().getCustomFont();
            notifLabel.setPosition(500, Gdx.graphics.getHeight()- notifLabel.getHeight() - 10);
            stage.addActor(notifLabel);
            float x = Gdx.graphics.getWidth() - timeLabel.getWidth() - 10;
            float y = Gdx.graphics.getHeight() - timeLabel.getHeight() - 10;

            timeLabel.setPosition(x, y);
            stage.addActor(timeLabel);

            ProgressBar.ProgressBarStyle style = new ProgressBar.ProgressBarStyle();
            style.background = skin.newDrawable("white",  new Color(0.2f, 0.2f, 0.2f, 1f));
            style.knobBefore = skin.newDrawable("white", new Color(92f / 255f, 116f/255f, 92f/255f, 1));
            style.knob = skin.newDrawable("white", Color.CLEAR);
            Player player = controller.getPlayerController().getPlayer();
            xpBar = new ProgressBar(0, player.getXpNeeded(), 1, false, style);
            xpBar.setWidth(300);
            xpBar.setHeight(40);
            xpBar.setValue(player.getXp());
            xpBar.setPosition(10, 20);
            stage.addActor(xpBar);
            int ammo = controller.getPlayerController().getPlayer().getWeapon().getAmmo();
            ammoLabel = new Label("ammo : " + ammo, skin);
            ammoLabel.setPosition(10, Gdx.graphics.getHeight()- levelLabel.getHeight() - 70);
            ammoLabel.setFontScale(1.1f);
            stage.addActor(ammoLabel);

            int hp = controller.getPlayerController().getPlayer().getHealthPoints();
            hpLabel = new Label("hp : " + hp, skin);
            hpLabel.setPosition(10, Gdx.graphics.getHeight() - levelLabel.getHeight() - 100);
            hpLabel.setFontScale(1.1f);
            stage.addActor(hpLabel);
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

                ScoreController.getController().addScore(new ScoreEntry(player.getUsername(), (int) (player.kills*GameSettings.gameTime*60f), player.kills, GameSettings.gameTime*60f));
                Main.getMain().setScreen(new EndScreen(true, player, (GameSettings.gameTime * 60f- controller.timeRemaining)));
                System.out.println("time death");
            }
            if (player.getHealthPoints() <= 0) {
                ScoreController.getController().addScore(new ScoreEntry(player.getUsername(), player.getTotalPoints(), player.kills, GameSettings.gameTime * 60f - controller.timeRemaining));
                Main.getMain().setScreen(new EndScreen(false, player, GameSettings.gameTime * 60f- controller.timeRemaining));
                System.out.println("hp death");
            }

            controller.updateGame(v);
            controller.checkBulletEnemyCollisions(bullets);
            Main.getBatch().begin();
            player.updateAbilities(v);
            player.getWeapon().update(v);
            controller.renderGame();

            Main.getBatch().end();
            levelLabel.setText(com.MinutesTillDawn.Model.Enums.Label.LEVEL.getText() + player.getLevel());
            killLabel.setText(com.MinutesTillDawn.Model.Enums.Label.KILLSCOUNT.getText() + player.kills);
            notifLabel.setText(notification);
            int minutes = (int) (controller.timeRemaining/60);
            int seconds = (int) (controller.timeRemaining % 60);
            timeLabel.setText(minutes + " : " + seconds);
            int ammo = player.getWeapon().getAmmo();
            ammoLabel.setText("ammo : " + ammo);
            hpLabel.setText("hp : " + player.getHealthPoints());
            xpBar.setValue(player.getXp());
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
