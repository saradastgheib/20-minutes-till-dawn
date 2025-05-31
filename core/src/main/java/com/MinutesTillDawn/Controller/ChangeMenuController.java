package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.MinutesTillDawn.View.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ChangeMenuController {
    private static ChangeMenuController controller;

    private ChangeMenu view;
    public static ChangeMenuController getController() {
        if (controller == null) controller = new ChangeMenuController();
        return controller;
    }

    public void setView(ChangeMenu view) {
        this.view = view;
        addListeners();
    }
    private void addListeners() {
        view.back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(MainMenu.getMainMenu());
            }
        });
        view.preGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new PreGameMenu(PreGameMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
        view.settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new SettingsMenu());
            }
        });
        view.scoreBoard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new Scoreboard());
            }
        });
        view.talent.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new hintMenu());
            }
        });
        view.profile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new ProfileMenu());
            }
        });
    }
}
