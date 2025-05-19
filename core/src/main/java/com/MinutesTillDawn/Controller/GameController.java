package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Model.Player;
import com.MinutesTillDawn.Model.User;
import com.MinutesTillDawn.Model.Weapon;
import com.MinutesTillDawn.View.GameScreen;

public class GameController {
    private GameScreen view;
    private WeaponController weaponController;
    private WorldController worldController;
    private PlayerController playerController;

    public void setView(GameScreen view) {
        this.view = view;
        weaponController = new WeaponController(new Weapon());
        playerController = new PlayerController(new Player(new User("sara", "sara", "hina", "sara", "sara"), false));
        worldController = new WorldController(playerController);
    }

    public void updateGame() {
        if (view != null) {

            playerController.update();
        }
    }

    public void renderGame() {
        worldController.update();
        playerController.render();
    }
    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }
}
