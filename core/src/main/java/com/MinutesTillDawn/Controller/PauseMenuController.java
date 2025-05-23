package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.MinutesTillDawn.View.MainMenu;
import com.MinutesTillDawn.View.PauseMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Timer;
import java.util.TimerTask;


public class PauseMenuController {
    private static PauseMenuController controller;
    private PauseMenu view;

    public static PauseMenuController getController() {
        if (controller == null) controller = new PauseMenuController();
        return controller;
    }

    public void setView(PauseMenu pauseMenu) {
        this.view = pauseMenu;
        addListeners();
    }

    public void addListeners() {
        view.resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(view.game);
            }
        });

        view.giveUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new MainMenu(MainMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
        view.bwCheckbox.addListener(event -> {
            GameAssetManager.getGameAssetManager().bwEnabled = view.bwCheckbox.isChecked();
            return false;
        });
        view.saveAndLeaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO save the game
                Main.getMain().setScreen(new MainMenu(MainMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
    }

}
