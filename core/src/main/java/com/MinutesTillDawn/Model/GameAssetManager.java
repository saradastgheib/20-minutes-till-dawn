package com.MinutesTillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private  Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private  Array<String> characterNames = new Array<>();
    private final HashMap<String, Animation<TextureRegion>> characterIdleAnimations = new HashMap<>();
    public boolean bwEnabled = false;
    private final String bullet = "bullet.png";
    private final  Array<TextureRegion> tiles = new Array<>();




    public GameAssetManager () {
        loadAvatars();
        loadCharacterIdleAnimation();
        initializeTiles();

    }


    public void initializeTiles() {
        for (int i = 1; i <= 18; i++) {
            Texture texture = new Texture("tiles/forest" + i + ".png");
            tiles.add(new TextureRegion(texture));
        }
    }
    public Array<TextureRegion> getForestTiles() {

        return tiles;
    }


    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null)
            gameAssetManager = new GameAssetManager();
        return gameAssetManager;
    }
    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public String getRandomAvatarPath() {
        return characterNames.random();
    }
    public void loadAvatars() {
        characterNames.add("abby");
        characterNames.add("dasher");
        characterNames.add("diamond");
        characterNames.add("hastur");
        characterNames.add("hina");
        characterNames.add("lilith");
        characterNames.add("luna");
        characterNames.add("raven");
        characterNames.add("scarlett");
        characterNames.add("shana");
        characterNames.add("spark");
        characterNames.add("yuki");
    }
    public void loadCharacterIdleAnimation() {
        for (String characterName : characterNames){
            Array<TextureRegion> frames = new Array<>();

            for (int i = 1; i < 6; i++) {
                String path = "characters/" + characterName + "/idle" + i + ".png";
                Texture texture = new Texture(Gdx.files.internal(path));
                TextureRegion region = new TextureRegion(texture);
                frames.add(region);
            }

            Animation<TextureRegion> idleAnimation = new Animation<>(0.1f, frames, Animation.PlayMode.LOOP);
            characterIdleAnimations.put(characterName, idleAnimation);
        }
    }
    public Animation<TextureRegion> getIdleAnimation(String characterFolder) {
        return characterIdleAnimations.get(characterFolder);
    }

    public String getBullet() {
        return bullet;
    }

    public Array<String> getCharacterNames() {
        return characterNames;
    }
}
