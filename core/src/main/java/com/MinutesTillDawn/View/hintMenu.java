package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class hintMenu implements Screen {
    private Stage stage;
    public TextButton cheatCodes, heroes;
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
        keys.getStyle().font = GameAssetManager.getGameAssetManager().getCustomFont();
        keys.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        Label currentKeys = new Label(GameSettings.controlScheme, skin);
        table.add(keys).padBottom(50);
        table.add(currentKeys).padRight(220).padBottom(50);
        table.row();

        cheatCodes = new TextButton(com.MinutesTillDawn.Model.Enums.Label.CHEATCODELABEL.getText(), skin);
        setButton(cheatCodes);
        table.add(cheatCodes).center().padBottom(50).padLeft(400);
        table.row();

        cheatCodes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                Dialog confirmDialog = new Dialog("", skin);

                confirmDialog.text(com.MinutesTillDawn.Model.Enums.Label.LCHEAT.getText() + "\n" + com.MinutesTillDawn.Model.Enums.Label.RCHEAT.getText() +
                    "\n" + com.MinutesTillDawn.Model.Enums.Label.HCHEAT.getText() + "\n" + com.MinutesTillDawn.Model.Enums.Label.BCHEAT.getText() +
                    "\n" + com.MinutesTillDawn.Model.Enums.Label.CCHEAT.getText() );
                TextButton okayButton = new TextButton("Okay", skin);
                hintMenu.this.setButton(okayButton);
                confirmDialog.button(okayButton, true);
                confirmDialog.setModal(true);
                confirmDialog.setMovable(false);
                confirmDialog.setColor(13f / 255f, 18f / 255f, 37f / 255f, 1f);
                confirmDialog.show(stage);
            }
        });
        table.row();
        heroes = new TextButton("heroes", skin);
        setButton(heroes);
        table.add(heroes).padBottom(50).padLeft(400);
        heroes.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Dialog confirmDialog = new Dialog("", skin);

                confirmDialog.text("Name: \nShana\nDiamond\nScarlet");
                confirmDialog.text("Hp: \n4\n7\n3");
                confirmDialog.text("Speed: \n4\n1\n5");
                TextButton okayButton = new TextButton("Okay", skin);
                hintMenu.this.setButton(okayButton);
                confirmDialog.button(okayButton, true);
                confirmDialog.setModal(true);
                confirmDialog.setMovable(false);
                confirmDialog.setColor(13f / 255f, 18f / 255f, 37f / 255f, 1f);
                confirmDialog.show(stage);
            }
        });

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
