package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.View.GameScreen;

public class GameController {
    private GameScreen view;
    private WeaponController weaponController;
    private WorldController worldController;
    private PlayerController playerController;

    public void setView(GameScreen view) {
        this.view = view;
        weaponController = new WeaponController();
        playerController = new PlayerController();
        worldController = new WorldController(playerController);
    }

    public void updateGame() {
        if (view != null);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }
}
