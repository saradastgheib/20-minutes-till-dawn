package com.MinutesTillDawn.Model;

import com.MinutesTillDawn.Model.Ability.*;
import com.MinutesTillDawn.Model.Enums.EnemyType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private  Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private final Array<String> characterNames = new Array<>();
    private final HashMap<String, Animation<TextureRegion>> characterIdleAnimations = new HashMap<>();
    private final  HashMap<String, Animation<TextureRegion>> enemyAnimations = new HashMap<>();
    public boolean bwEnabled = false;
    private final  Array<TextureRegion> tiles = new Array<>();
    private final Sprite Cursor = new Sprite(new Texture("cursor.png"));
    private Animation<TextureRegion> enemyDeath;
    BitmapFont customFont;
    private final Array<String> abilities = new Array<>();



    public GameAssetManager () {
        loadAvatars();
        loadCharacterIdleAnimation();
        loadEnemyAnimation();
        loadDeathAnimation();
        initializeTiles();
        initializeAbilities();
        loadFont();
    }

    private void initializeAbilities() {
        abilities.add("amocrease");
        abilities.add("damage");
        abilities.add("procrease");
        abilities.add("speedy");
        abilities.add("vitality");
    }

    public Ability getRandomAbility () {
        String ability = abilities.random();
        Ability ability1 = null;
        switch (ability) {
            case "amocrease" :
                ability1 =  new Amocrease();
                break;
            case "damage" :
                ability1 =  new Damage();
                break;

            case "procrease" :
                ability1 =  new Pocrease();
                break;

            case "speedy" :
                ability1 =  new Speedy();
                break;
            case "vitality" :
                ability1 =  new Vitality();
                break;

        }
        return ability1;
    }
    public void initializeTiles() {
        for (int i = 1; i <= 18; i++) {
            Texture texture = new Texture("tiles/forest" + i + ".png");
            System.out.println("Creating forest tile texture: forest" + i + ".png, handle: " + texture.getTextureObjectHandle());
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
    public void loadEnemyAnimation() {
        for (EnemyType type : EnemyType.values()) {
            Array<TextureRegion> frames = new Array<>();
            String name = type.name().toLowerCase();
            for (int i = 1; i < 5; i++) {
                String path = "monsters/" + name + "/pic" + i + ".png";
                Texture texture = new Texture(Gdx.files.internal(path));
                TextureRegion region = new TextureRegion(texture);
                frames.add(region);
            }
            Animation<TextureRegion> animation =  new Animation<>(0.2f, frames, Animation.PlayMode.LOOP);
            enemyAnimations.put(name, animation);
        }
    }
    public void loadDeathAnimation() {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i <=5; i++) {
            String path = "explosion/FireballExplosion_" + i + ".png";
            Texture texture = new Texture(Gdx.files.internal(path));
            TextureRegion region = new TextureRegion(texture);
            frames.add(region);
        }
        enemyDeath = new Animation<>(0.1f, frames, Animation.PlayMode.NORMAL);
    }

    public Animation<TextureRegion> getDeathAnimation() {
        return enemyDeath;
    }
    public Animation<TextureRegion> getIdleAnimation(String characterFolder) {
        return characterIdleAnimations.get(characterFolder);
    }

    public Animation<TextureRegion> getEnemyAnimation (String name) {
        return enemyAnimations.get(name);
    }

    public Array<String> getCharacterNames() {
        return characterNames;
    }

    public Sprite getCursor() {
        return Cursor;
    }

    public void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ChevyRay-Lantern.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;

        customFont = generator.generateFont(parameter);
        generator.dispose();
    }

    public BitmapFont getCustomFont() {
        return customFont;
    }
}
