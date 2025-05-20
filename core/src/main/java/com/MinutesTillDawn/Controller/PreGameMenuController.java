package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.Player;
import com.MinutesTillDawn.Model.User;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.View.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Timer;
import java.util.TimerTask;

public class PreGameMenuController {
    private static PreGameMenuController controller;
    private PreGameMenu view;
    public PreGameMenuController() {
    }

    public static PreGameMenuController getController() {
        if (controller == null)  controller = new PreGameMenuController();
        return controller;
    }
    public void setView(PreGameMenu view, ImageButton[] characters, ImageButton[] weapons) {
        this.view = view;
        addListeners(characters, weapons);
    }

    private void addListeners(ImageButton[] characters, ImageButton[] weapons) {
        for (ImageButton btn : characters) {
            btn.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    view.largePreview.setDrawable(new TextureRegionDrawable(new Texture("characters/" + btn.getName() + "/avatar.png")));
                    UserDatabase.getDatabase().getCurrentUser().setSelectedHero(btn.getName());
                    view.characterName.setText(btn.getName());
                }
            });
        }

        for (ImageButton btn : weapons) {
            btn.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    view.weaponName.setText(btn.getName());
                    UserDatabase.getDatabase().getCurrentUser().setSelectedWeapon(btn.getName());
                }
            });
        }
        view.playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new GameScreen(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
    }
}

