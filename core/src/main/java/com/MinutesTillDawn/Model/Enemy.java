package com.MinutesTillDawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    private Texture enemyTexture;
    private Sprite enemySprite;
    private float posX = 950, posY = 500;

    public Enemy(String type) {
        enemyTexture = new Texture("T_GhostMonster_4.png");
        enemySprite = new Sprite(enemyTexture);
    }
    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public Vector2 getPosition() {
        return new Vector2(posX + enemySprite.getWidth()/2, posY + enemySprite.getHeight()/2);
    }
}
