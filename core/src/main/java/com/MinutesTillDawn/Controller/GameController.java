package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Model.*;
import com.MinutesTillDawn.View.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private GameScreen view;
    private WeaponController weaponController;
    private WorldController worldController;
    private PlayerController playerController;
    public float timeRemaining;
    public Vector2 virtualMousePos = new Vector2();
    public boolean aimAutoEnabled = false;
    public List<Enemy> enemies = new ArrayList<>();



    public void setView(GameScreen view) {
        this.view = view;
        Player orgPlayer = UserDatabase.getDatabase().getCurrentUser();
        Player player = new Player(orgPlayer.getUser(), orgPlayer.isGuest);
        timeRemaining = GameSettings.gameTime * 60f;
        Weapon weapon = new Weapon(player.selectedWeapon);
        weaponController = new WeaponController(weapon);
        player.setWeapon(weapon);
        playerController = new PlayerController(player);
        worldController = new WorldController(playerController);
    }

    public void updateGame() {
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

            playerController.update();
            weaponController.update(playerController.getPlayer());
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
}
