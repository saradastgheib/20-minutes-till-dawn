package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.*;
import com.MinutesTillDawn.Model.Enums.EnemyType;
import com.MinutesTillDawn.View.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameController {
    private GameScreen view;
    private WeaponController weaponController;
    private WorldController worldController;
    private PlayerController playerController;
    private EnemyController enemyController;
    public float timeRemaining;
    public Vector2 virtualMousePos = new Vector2();
    public boolean aimAutoEnabled = false;
    public List<Enemy> enemies = new ArrayList<>();
    private float invincibleTimer = 0f;


    public void setView(GameScreen view) {
        this.view = view;
        Player orgPlayer = UserDatabase.getDatabase().getCurrentUser();
        Player player = new Player(orgPlayer.getUser(), orgPlayer.isGuest);
        timeRemaining = GameSettings.gameTime * 60f;
        Weapon weapon = new Weapon(player.selectedWeapon);
        weaponController = new WeaponController(weapon);
        player.setWeapon(weapon);
        playerController = new PlayerController(player);
        worldController = new WorldController(playerController, this);
        enemyController = new EnemyController(this);
    }

    public void updateGame(float v) {
        if (view != null) {
            if (aimAutoEnabled) {
                Enemy target = getClosestEnemy(playerController.player.getCenter(), enemies);
                if (target != null) {
                    virtualMousePos.set(target.getPosition());
                }
            } else {
                virtualMousePos.set(Gdx.input.getX(), Gdx.input.getY());
                view.getStage().getViewport().unproject(virtualMousePos);
            }

            playerController.update(v);
            weaponController.update(playerController.getPlayer());
            updateInvincibility(v);
            enemyController.update(v);

        }
    }

    Enemy getClosestEnemy(Vector2 playerPos, List<Enemy> enemies) {
        Enemy closest = null;
        float minDist = Float.MAX_VALUE;
        for (Enemy enemy : enemies) {
            float dist = playerPos.dst(enemy.getPosition());
            if (dist < minDist) {
                minDist = dist;
                closest = enemy;
            }
        }
        return closest;
    }

    public void renderGame() {
        worldController.update();
       playerController.render();
        weaponController.render();
        enemyController.render();
        Sprite cursor = GameAssetManager.getGameAssetManager().getCursor();
        cursor.setPosition(virtualMousePos.x, virtualMousePos.y);
        cursor.draw(Main.getBatch());
    }
    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public void spawnEnemy(EnemyType enemyType) {
        OrthographicCamera camera = worldController.getCamera();
        float x, y;
        int side = MathUtils.random(3);

        float camX = camera.position.x;
        float camY = camera.position.y;
        float viewWidth = camera.viewportWidth;
        float viewHeight = camera.viewportHeight;

        switch (side) {
            case 0: // Top
                x = MathUtils.random(camX - viewWidth / 2f, camX + viewWidth / 2f);
                y = camY + viewHeight / 2f + 50;
                break;
            case 1: // Right
                x = camX + viewWidth / 2f + 50;
                y = MathUtils.random(camY - viewHeight / 2f, camY + viewHeight / 2f);
                break;
            case 2: // Bottom
                x = MathUtils.random(camX - viewWidth / 2f, camX + viewWidth / 2f);
                y = camY - viewHeight / 2f - 50;
                break;
            default: // Left
                x = camX - viewWidth / 2f - 50;
                y = MathUtils.random(camY - viewHeight / 2f, camY + viewHeight / 2f);
                break;
        }

        System.out.println("enemy on  x : " + x + " y: " + y);
        Enemy enemy = new Enemy(enemyType);
        enemy.setPosition(x, y);
        enemies.add(enemy);
    }

    public void makeInvincible() {
        playerController.player.isInvincible = true;
    }

    public void updateInvincibility(float v) {
        if (playerController.player.isInvincible) {
            invincibleTimer += v;
            if (invincibleTimer >= 1f) {
                invincibleTimer = 0;
                playerController.player.isInvincible = false;
            }
        }
    }

    public void checkBulletEnemyCollisions(Array<Bullet> bullets) {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Circle bulletCircle = bullet.getBoundingCircle();

            for (Enemy enemy : enemies) {
                if (enemy.state!= EnemyState.DEAD && Intersector.overlaps(bulletCircle, enemy.getEnemySprite().getBoundingRectangle())) {
                    enemy.takeDamage(10);
                    bulletIterator.remove();
                    break;
                }
            }
        }
    }

}
