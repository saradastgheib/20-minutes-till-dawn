package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.View.ChangeMenu;
import com.MinutesTillDawn.View.MainMenu;
import com.MinutesTillDawn.View.PreGameMenu;
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
                Main.getMain().setScreen(PreGameMenu.getPreGameMenu());
            }
        });
    }
}
