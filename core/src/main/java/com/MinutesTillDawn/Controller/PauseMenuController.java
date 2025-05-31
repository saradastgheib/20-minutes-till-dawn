package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.Enemy;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.MinutesTillDawn.Model.Player;
import com.MinutesTillDawn.Model.saveStuff.EnemyData;
import com.MinutesTillDawn.Model.saveStuff.SaveData;
import com.MinutesTillDawn.View.MainMenu;
import com.MinutesTillDawn.View.PauseMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

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
                saveGame(view.game.getController().getPlayerController().getPlayer(), view.game.getController());
                Main.getMain().setScreen(new MainMenu(MainMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
    }
    public void saveGame(Player player, GameController controller) {
        SaveData data = new SaveData();
        data.username = player.getUsername();
        data.playerX = player.getPosX();
        data.playerY = player.getPosY();
        data.playerHP = player.getHealthPoints();
        data.kills = player.kills;
        data.timeRemaining = controller.timeRemaining;

        data.enemies = new Array<>();
        for (Enemy enemy : controller.enemies) {
            EnemyData e = new EnemyData();
            e.x = enemy.getX();
            e.y = enemy.getY();
            e.type = enemy.getType();
            e.hp = enemy.getHealth();
            data.enemies.add(e);
        }

        Json json = new Json();
        FileHandle file = Gdx.files.local(data.username + ".json");
        file.writeString(json.prettyPrint(data), false);
    }

}
