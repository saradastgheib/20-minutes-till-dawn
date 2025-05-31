package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.Model.saveStuff.SaveData;
import com.MinutesTillDawn.View.ChangeMenu;
import com.MinutesTillDawn.View.GameScreen;
import com.MinutesTillDawn.View.LoginMenu;
import com.MinutesTillDawn.View.MainMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
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
        view.playSavedGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SaveData data = loadGame();
                if (data != null) {
                    Main.getMain().setScreen(new GameScreen(data));
                }
            }
        });

    }
    public SaveData loadGame() {
        FileHandle file = Gdx.files.local(UserDatabase.getDatabase().getCurrentUser().getUsername() + ".json");
        if (!file.exists()) return null;

        Json json = new Json();
        return json.fromJson(SaveData.class, file);
    }

    public static MainMenuController getController() {
        if (controller == null) controller = new MainMenuController();
        return controller;
    }
}
