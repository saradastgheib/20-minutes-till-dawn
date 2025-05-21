package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.SettingsMenuController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsMenu implements Screen {
    private Stage stage;
    public Label title;
    private SettingsMenuController controller;
    private Skin skin;
    public SettingsMenu () {
        title = new Label("-SETTINGS-", GameAssetManager.getGameAssetManager().getSkin(), "title");
        controller = SettingsMenuController.getController();
        skin = GameAssetManager.getGameAssetManager().getSkin();
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.pad(30);
        stage.addActor(table);

        title.setFontScale(2f);
        table.add(title).colspan(2).padBottom(40).center();
        table.row();
        Label musicVolumeLabel = new Label("music volume: ", skin);
        Slider musicVolumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        musicVolumeSlider.setValue(GameSettings.getCurrentMusic().getVolume());
        table.add(musicVolumeLabel).left().padBottom(20);
        table.add(musicVolumeSlider).width(300).padBottom(20);
        table.row();
        // Toggle SFX
            Label toggleSfxLabel = new Label("SFX", skin);
            CheckBox sfxCheckbox = new CheckBox("", skin);
            sfxCheckbox.setChecked(GameSettings.sfxEnabled);
//            sfxCheckbox.addListener(event -> {
//                GameSettings.sfxEnabled = sfxCheckbox.isChecked();
//                return false;
//            });
            table.add(toggleSfxLabel).left().padBottom(20);
            table.add(sfxCheckbox).padBottom(20);
            table.row();
        TextButton remapControlsButton = new TextButton("keyboard controllers: ", skin);
        remapControlsButton.addListener(event -> {
            Dialog dialog = new Dialog("", skin) {
                protected void result(Object object) {
                    if ("WASD".equals(object)) {
                        GameSettings.controlScheme = "WASD";
                    } else if ("ARROWS".equals(object)) {
                        GameSettings.controlScheme = "ARROWS";
                    }
                }
            };

            dialog.text("choose your controllers");
            dialog.button("WASD", "WASD");
            dialog.button("Arrows", "ARROWS");
            dialog.show(stage);

            return false;
        });
        table.add(remapControlsButton).colspan(2).padBottom(20);
        table.row();

        // 🔁 Auto-reload toggle
        Label autoReloadLabel = new Label("auto reload: ", skin);
        CheckBox autoReloadCheckbox = new CheckBox("", skin);
        autoReloadCheckbox.setChecked(GameSettings.autoReloadEnabled);
//        autoReloadCheckbox.addListener(event -> {
//            autoReloadEnabled = autoReloadCheckbox.isChecked();
//            return false;
//        });
        table.add(autoReloadLabel).left().padBottom(20);
        table.add(autoReloadCheckbox).padBottom(20);
        table.row();
        Label bwLabel = new Label("black and white display: ", skin);
        CheckBox bwCheckbox = new CheckBox("", skin);
        bwCheckbox.setChecked(GameAssetManager.getGameAssetManager().bwEnabled);
//        bwCheckbox.addListener(event -> {
//            GameAssetManager.getGameAssetManager().bwEnabled = bwCheckbox.isChecked();
//            return false;
//        });
        table.add(bwLabel).left().padBottom(40);
        table.add(bwCheckbox).padBottom(40);
        table.row();
        TextButton backButton = new TextButton("back", skin);
//        backButton.addListener(event -> {
//            game.setScreen(previousScreen); // فرضی
//            return false;
//        });
        table.add(backButton).colspan(2).center().padTop(20);
    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(new Color(13f / 255f, 18f / 255f, 37f / 255f, 1f));

        Batch batch = Main.getBatch();
        if (GameAssetManager.getGameAssetManager().bwEnabled) {
            ScreenUtils.clear(Color.BLACK);
            batch.setShader(Main.getMain().grayscaleShader);
        } else {
            batch.setShader(null);
        }

        Main.getBatch().begin();
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
