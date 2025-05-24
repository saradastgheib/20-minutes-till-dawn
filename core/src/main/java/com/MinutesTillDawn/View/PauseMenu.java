package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.PauseMenuController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.Ability.Ability;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseMenu implements Screen {
    private PauseMenuController controller;
    public Stage stage;
    private Skin skin;
    public TextButton resumeButton, giveUpButton, saveAndLeaveButton;
    public GameScreen game;
    public CheckBox bwCheckbox;

    public PauseMenu(GameScreen game) {
        controller = PauseMenuController.getController();
        skin = GameAssetManager.getGameAssetManager().getSkin();
        resumeButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.RESUME.getText(), skin);
        giveUpButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.GIVEUP.getText(), skin);
        bwCheckbox = new CheckBox("", skin);
        saveAndLeaveButton = new TextButton(com.MinutesTillDawn.Model.Enums.Label.SAVEANDLEAVE.getText(), skin);
        this.game = game;
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
        setButton(resumeButton);
        table.add(resumeButton).pad(20);
        table.row();
        Label label1= new Label(com.MinutesTillDawn.Model.Enums.Label.CHEATCODELABEL.getText(), skin);
        label1.setColor(92f/255f,116f/255f,92f/255f,255f/255f);
        label1.setFontScale(1.2f);
        table.add(label1);
        table.row();

        Label lLabel = new Label(com.MinutesTillDawn.Model.Enums.Label.LCHEAT.getText(), skin);
        table.add(lLabel);
        table.row();
        Label rLabel = new Label(com.MinutesTillDawn.Model.Enums.Label.RCHEAT.getText(), skin);
        table.add(rLabel);
        table.row();
        Label hLabel = new Label(com.MinutesTillDawn.Model.Enums.Label.HCHEAT.getText(), skin);
        table.add(hLabel);
        table.row();
        Label bLabel = new Label(com.MinutesTillDawn.Model.Enums.Label.BCHEAT.getText(), skin);
        table.add(bLabel);
        table.row();
        Label cLabel = new Label(com.MinutesTillDawn.Model.Enums.Label.CCHEAT.getText(), skin);
        table.add(cLabel);

        table.row();

        Label label2 = new Label(com.MinutesTillDawn.Model.Enums.Label.ABILITIESLABEL.getText(), skin);
        label2.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        label2.setFontScale(1.2f);
        table.add(label2);

        for (Ability ability : game.getController().getPlayerController().getPlayer().getRetrievedAbilities()) {
            table.row();
            Label label = new Label(ability.toString().toLowerCase(), skin);
            table.add(label);
        }

        table.row();
        setButton(giveUpButton);
        table.add(giveUpButton).padTop(30);

        Label bwLabel = new Label(com.MinutesTillDawn.Model.Enums.Label.BWDISPLAY.getText(), skin);

        table.row();
        bwCheckbox.setChecked(GameAssetManager.getGameAssetManager().bwEnabled);
        bwCheckbox.setColor(new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f));
        table.add(bwLabel).left().padTop(40);
        table.add(bwCheckbox).padTop(40);
        table.row();

        setButton(saveAndLeaveButton);
        table.add(saveAndLeaveButton).padTop(40);

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
