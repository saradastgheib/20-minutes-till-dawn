package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Model.*;
import com.MinutesTillDawn.View.GameScreen;

public class GameController {
    private GameScreen view;
    private WeaponController weaponController;
    private WorldController worldController;
    private PlayerController playerController;
    public float timeRemaining;
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
            playerController.update();
            weaponController.update(playerController.getPlayer());
        }
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
