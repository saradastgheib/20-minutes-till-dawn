package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.MinutesTillDawn.View.ChangeMenu;
import com.MinutesTillDawn.View.SettingsMenu;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class SettingsMenuController {
    private static SettingsMenuController controller;
    private SettingsMenu view;

    public static SettingsMenuController getController() {
        if (controller == null) controller = new SettingsMenuController();
        return controller;
    }
    public void setView(SettingsMenu view) {
        this.view = view;
        addListeners();
    }
    private void addListeners(){
        view.musicVolumeSlider.addListener(new DragListener(){
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                GameSettings.setVolume(view.musicVolumeSlider.getValue());
            }
        });
        view.changeMusicButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                GameSettings.musics.get(GameSettings.currentTrackIndex[0]).stop();

                GameSettings.currentTrackIndex[0] = (GameSettings.currentTrackIndex[0] + 1) % GameSettings.musics.size;

                Music next = GameSettings.musics.get(GameSettings.currentTrackIndex[0]);
                next.setLooping(true);
                next.play();
            }
        });
        view.sfxCheckbox.addListener(event -> {
                GameSettings.sfxEnabled = view.sfxCheckbox.isChecked();
                return false;
           });
        view.autoReloadCheckbox.addListener(event -> {
            GameSettings.autoReloadEnabled = view.autoReloadCheckbox.isChecked();
            return false;
        });
        view.bwCheckbox.addListener(event -> {
            GameAssetManager.getGameAssetManager().bwEnabled = view.bwCheckbox.isChecked();
            return false;
        });
        view.backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new ChangeMenu(ChangeMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
        view.languageToggle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (GameSettings.language.equals("english")) GameSettings.language = "french";
                else GameSettings.language = "english";
                System.out.println("clicked");
            }
        });
    }
}
