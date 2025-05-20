package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.View.ChangeMenu;
import com.MinutesTillDawn.View.LoginMenu;
import com.MinutesTillDawn.View.MainMenu;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.MinutesTillDawn.View.PreGameMenu;

public class MainMenuController {
    private  static MainMenuController controller;
    private MainMenu view;
    public void setView(MainMenu view) {
        this.view = view;
        addListeners();
    }

    private void addListeners() {
        view.changeMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(ChangeMenu.getChangeMenu());
            }
        });
        view.logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(LoginMenu.getLoginMenu());
            }
        });

    }
    public static MainMenuController getController() {
        if (controller == null) controller = new MainMenuController();
        return controller;
    }
}
