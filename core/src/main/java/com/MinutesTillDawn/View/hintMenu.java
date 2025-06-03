package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.HintController;
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

public class hintMenu implements Screen {
    private Stage stage;
    private HintController controller = new HintController();
    public hintMenu() {

    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        Label keys = new Label("Keys:", skin);
        keys.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        Label currentKeys = new Label(GameSettings.controlScheme, skin);
        table.add(keys).padRight(60).padBottom(50);
        table.add(currentKeys).padLeft(60).padBottom(50);
        table.row();

        Label label1= new Label(com.MinutesTillDawn.Model.Enums.Label.CHEATCODELABEL.getText(), skin);
        label1.setColor(92f/255f,116f/255f,92f/255f, 1.0f);
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
    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(new Color(13f / 255f, 18f / 255f, 37f / 255f, 1f));

        Batch batch = Main.getBatch();
        if (GameAssetManager.getGameAssetManager().bwEnabled) {
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
