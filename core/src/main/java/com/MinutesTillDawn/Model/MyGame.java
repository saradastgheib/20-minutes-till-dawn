package com.MinutesTillDawn.Model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class MyGame extends Game {
    public ShaderProgram grayscaleShader;
    public static MyGame game;

    public static MyGame getGame() {
        if (game == null) game = new MyGame();
        return game;
    }

    @Override
    public void create() {



    }
}
