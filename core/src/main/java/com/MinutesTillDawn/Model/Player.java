package com.MinutesTillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
//    private Texture playerTexture = null;
//    private Sprite plaerSprite = new Sprite(playerTexture);
    public boolean isGuest;
    private User user;
    private int pointsInThisGame = 0;
    private float speed = 5, health =100;
    private float posX = 0, posY = 0;
    private CollisionRect rect;
    private float time;

    public Player(User user, boolean isGuest) {
        this.user = user;
        this.isGuest = isGuest;
//        plaerSprite.setPosition(Gdx.graphics.getWidth() /2 , Gdx.graphics.getHeight()/2);
//        plaerSprite.setSize(playerTexture.getWidth()*3, playerTexture.getHeight()*3);
//        rect = new CollisionRect(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, playerTexture.getWidth()*2, playerTexture.getHeight()*2);
    }

    public String getAvatarPath() {
        return user.getAvatarPath();
    }

    public String getUsername () {
        return  user.getUsername();
    }

    public int getTotalPoints() {
        return user.getTotalPoints();
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }
}
