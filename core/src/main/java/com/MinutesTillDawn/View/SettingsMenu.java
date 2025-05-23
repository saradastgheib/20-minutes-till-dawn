package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.SettingsMenuController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class SettingsMenu implements Screen {
    private Stage stage;
    public Label title;
    private SettingsMenuController controller;
    private Skin skin;
    public Slider musicVolumeSlider;
    public TextButton changeMusicButton, backButton, languageToggle;
    public CheckBox sfxCheckbox, autoReloadCheckbox, bwCheckbox;


    public SettingsMenu () {
        title = new Label("-"+ com.MinutesTillDawn.Model.Enums.Label.SETTINGS.getText().toUpperCase() +"-", GameAssetManager.getGameAssetManager().getSkin(), "title");
        controller = SettingsMenuController.getController();
        skin = GameAssetManager.getGameAssetManager().getSkin();
        musicVolumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        changeMusicButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.CHANGEMUSIC.getText(), skin);
        sfxCheckbox = new CheckBox("", skin);
        autoReloadCheckbox = new CheckBox("", skin);
        bwCheckbox = new CheckBox("", skin);
        languageToggle = new TextButton(com.MinutesTillDawn.Model.Enums.Label.LANGUAGE.getText(), skin);
        backButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.BACK.getText(), skin);
        controller.setView(this);
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport(), Main.getBatch());
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.pad(30);
        stage.addActor(table);

        title.setFontScale(2f);
        table.add(title).colspan(2).padBottom(30).center();
        table.row();
        Label musicVolumeLabel = new Label(com.MinutesTillDawn.Model.Enums.Label.MUSICVOLUME.getText(), skin);

        musicVolumeSlider.setValue(GameSettings.getCurrentMusic().getVolume());
        table.add(musicVolumeLabel).left().padBottom(15);
        table.add(musicVolumeSlider).width(300).padBottom(15);
        table.row();

        table.add(changeMusicButton).colspan(2).padBottom(15);
        table.row();
        // Toggle SFX
            Label toggleSfxLabel = new Label("SFX", skin);

            sfxCheckbox.setChecked(GameSettings.sfxEnabled);

            table.add(toggleSfxLabel).left().padBottom(15);
            table.add(sfxCheckbox).padBottom(15);
            table.row();
        TextButton remapControlsButton = getTextButton();
        table.add(remapControlsButton).colspan(2).padBottom(15);
        table.row();

        Label autoReloadLabel = new Label(com.MinutesTillDawn.Model.Enums.Label.AUTORELOAD.getText(), skin);

        autoReloadCheckbox.setChecked(GameSettings.autoReloadEnabled);

        table.add(autoReloadLabel).left().padBottom(15);
        table.add(autoReloadCheckbox).padBottom(15);
        table.row();
        Label bwLabel = new Label(com.MinutesTillDawn.Model.Enums.Label.BWDISPLAY.getText(), skin);

        bwCheckbox.setChecked(GameAssetManager.getGameAssetManager().bwEnabled);

        table.add(bwLabel).left().padBottom(15);
        table.add(bwCheckbox).padBottom(15);
        table.row();
        Label languageLabel = new Label("language: ", skin);
        table.add(languageLabel).left().padBottom(15);
        table.add(languageToggle).padBottom(15);
        table.row();
        table.add(backButton).colspan(2).center();

    }

    private TextButton getTextButton() {
        TextButton remapControlsButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.CONTROLLERSLABEL.getText(), skin);
        remapControlsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Dialog dialog = new Dialog("", skin) {
                    @Override
                    protected void result(Object object) {
                        if ("WASD".equals(object)) {
                            GameSettings.controlScheme = "WASD";
                        } else if ("ARROWS".equals(object)) {
                            GameSettings.controlScheme = "ARROWS";
                        }
                    }
                };

                dialog.text(com.MinutesTillDawn.Model.Enums.Label.CHOOSELABEL.getText());
                dialog.button("WASD", "WASD");
                dialog.button(com.MinutesTillDawn.Model.Enums.Label.ARROWS.getText(), "ARROWS");
                dialog.show(stage);

            }
        });
        return remapControlsButton;
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
    private void setButton(TextButton button) {
        button.setColor(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        button.getStyle().fontColor = Color.WHITE;
        button.getStyle().overFontColor= new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
    }
}
