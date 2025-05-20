package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Model.Player;
import com.MinutesTillDawn.Model.User;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.Model.Weapon;
import com.MinutesTillDawn.View.GameScreen;

public class GameController {
    private GameScreen view;
    private WeaponController weaponController;
    private WorldController worldController;
    private PlayerController playerController;

    public void setView(GameScreen view) {
        this.view = view;
        Player player = UserDatabase.getDatabase().getCurrentUser();
        System.out.println("set current player : " + player.getUsername());
        weaponController = new WeaponController(new Weapon(player.selectedWeapon));
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
//        playerController.render();
//        weaponController.render();
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
