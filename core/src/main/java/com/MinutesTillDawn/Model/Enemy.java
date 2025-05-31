package com.MinutesTillDawn.Model;

import com.MinutesTillDawn.Model.Enums.EnemyType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Locale;

public class Enemy {
    private Texture enemyTexture;
    private Sprite enemySprite;
    public TextureRegion region;
    private float posX = 950, posY = 500;
    private int hp;
    private final EnemyType type;
    public Enemy(EnemyType type) {
        enemyTexture = type.getTexture();
        enemySprite = new Sprite(enemyTexture);
        enemySprite.setSize(enemyTexture.getWidth()*3, enemyTexture.getHeight() * 3);

        hp = type.getHp();
        this.type = type;
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

    public int getHealth() {
        return  hp;
    }

    public String getType() {
        return type.name().toLowerCase();
    }

    public void setPosition(float x, float y) {
        posX = x;
        posY = y;
        enemySprite.setPosition(posX, posY);
    }
    public void setHealth(int health) {
        hp = health;
    }

    public Sprite getEnemySprite() {
        return enemySprite;
    }
}
