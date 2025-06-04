package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.ChangeAvatarController;
import com.MinutesTillDawn.Controller.PreGameMenuController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.MinutesTillDawn.Model.UserDatabase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ChangeAvatar implements Screen {
    private Stage stage;
    public Table table;
    public ImageButton abby, dasher, diamond, hastur, hina, lilith, luna, raven, scarlett, shana, spark, yuki;
    public Image largePreview;
    public Label characterName, weaponName, gameTime;
    ImageButton[] characters;
    private final ChangeAvatarController controller;
    public TextButton back;
    public ChangeAvatar(ChangeAvatarController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        TextureRegionDrawable abbyIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/abby/idle1.png")));
        abby = new ImageButton(abbyIcon);
        abby.setName("abby");
        TextureRegionDrawable dasherIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/dasher/idle1.png")));
        dasher = new ImageButton(dasherIcon);
        dasher.setName("dasher");
        TextureRegionDrawable diamondIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/diamond/idle1.png")));
        diamond = new ImageButton(diamondIcon);
        diamond.setName("diamond");
        TextureRegionDrawable hasturIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/hastur/idle1.png")));
        hastur = new ImageButton(hasturIcon);
        hastur.setName("hastur");
        TextureRegionDrawable hinaIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/hina/idle1.png")));
        hina = new ImageButton(hinaIcon);
        hina.setName("hina");
        TextureRegionDrawable lilithIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/lilith/idle1.png")));
        lilith = new ImageButton(lilithIcon);
        lilith.setName("lilith");
        TextureRegionDrawable lunaIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/luna/idle1.png")));
        luna = new ImageButton(lunaIcon);
        luna.setName("luna");
        TextureRegionDrawable ravenIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/raven/idle1.png")));
        raven = new ImageButton(ravenIcon);
        raven.setName("raven");
        TextureRegionDrawable scarlettIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/scarlett/idle1.png")));
        scarlett = new ImageButton(scarlettIcon);
        scarlett.setName("scarlett");
        TextureRegionDrawable shanaIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/shana/idle1.png")));
        shana = new ImageButton(shanaIcon);
        shana.setName("shana");
        TextureRegionDrawable sparkIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/spark/idle1.png")));
        spark = new ImageButton(sparkIcon);
        spark.setName("spark");
        TextureRegionDrawable yukiIcon = new TextureRegionDrawable(new TextureRegion(new Texture("characters/yuki/idle1.png")));
        yuki = new ImageButton(yukiIcon);
        yuki.setName("yuki");
        characterName = new Label("", skin);
        characterName.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        characters = new ImageButton[]{abby, dasher, diamond, hastur, hina, lilith, luna, raven, scarlett, shana, spark, yuki};
        weaponName = new Label("", skin);
        weaponName.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        gameTime = new Label("", skin);
        gameTime.setColor(253f / 255f, 81f / 255f, 97f / 255f, 1f);
        back = new TextButton("back", skin);
        controller.setView(this, characters);
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport(), Main.getBatch());
        Gdx.input.setInputProcessor(stage);
        table.setTouchable(Touchable.enabled);
        table.setFillParent(true);
        table.top().right();
        stage.addActor(table);
        largePreview = new Image();
        largePreview.setDrawable(new TextureRegionDrawable(new Texture("characters/" + UserDatabase.getDatabase().getCurrentUser().getCharacterName() + "/avatar.png")));
        Table characterIconsTable = new Table();
        characterIconsTable.top().left();

        for (ImageButton btn : characters) {
            btn.setSize(btn.getWidth() * 4, btn.getHeight() * 4);
            btn.getImage().setScaling(Scaling.fill);
            btn.getImage().setFillParent(true);
            btn.setTouchable(Touchable.enabled);
            characterIconsTable.add(btn).size(btn.getWidth(), btn.getHeight()).pad(30);
        }
        characterIconsTable.row();
        table.add(characterIconsTable).colspan(2).padTop(30).top().left();
        table.row();
//

        table.add(largePreview).padLeft(10).right();
        Table rightSide = new Table();
        characterName.setFontScale(3f);
        characterName.setText(UserDatabase.getDatabase().getCurrentUser().getCharacterName());
        rightSide.add(characterName).left().padRight(100);
        rightSide.add(largePreview).size(405, 470).padRight(60).padTop(30);
        rightSide.center().right();
        table.add(rightSide).expand().right().top();
        table.row();
        setButton(back);
        back.setScale(4f);
        table.add(back).padTop(30).padBottom(100).bottom();
    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(new Color(13f/255f,18f/255f,37f/255f,255f/255f));
        Batch batch = Main.getBatch();
        if (GameAssetManager.getGameAssetManager().bwEnabled) {
            ScreenUtils.clear(Color.BLACK);
            batch.setShader(Main.getMain().grayscaleShader);
        } else {
            batch.setShader(null);
        }

        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(v);
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
