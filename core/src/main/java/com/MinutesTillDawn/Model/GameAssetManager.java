package com.MinutesTillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private  Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private  Array<String> avatarPaths = new Array<>();
    Array<Texture> loadedAvatars = new Array<>();
    public boolean bwEnabled =true;

    public Texture getTextureForAvatar(String path) {
        Texture t = new Texture(Gdx.files.internal(path));
        loadedAvatars.add(t);
        return t;
    }

    public void dispose() {
        for (Texture tex : loadedAvatars) {
            tex.dispose();
        }
    }

    public GameAssetManager () {
        gameAssetManager = this;
        loadAvatars();
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
        return avatarPaths.random();
    }
    public void loadAvatars() {
        avatarPaths.add("avatars/T_Abby_Portrait.png");
        avatarPaths.add("avatars/T_Dasher_Portrait.png");
        avatarPaths.add("avatars/T_Diamond_Portrait.png");
        avatarPaths.add("avatars/T_Hastur_Portrait.png");
        avatarPaths.add("avatars/T_Hina_Portrait.png");
        avatarPaths.add("avatars/T_Lilith_Portrait.png");
        avatarPaths.add("avatars/T_Luna_Portrait.png");
        avatarPaths.add("avatars/T_Raven_Portrait.png");
        avatarPaths.add("avatars/T_Scarlett_Portrait.png");
        avatarPaths.add("avatars/T_Shana_Portrait.png");
        avatarPaths.add("avatars/T_Spark_Portrait.png");
        avatarPaths.add("avatars/T_Yuki_Portrait.png");
    }
}
