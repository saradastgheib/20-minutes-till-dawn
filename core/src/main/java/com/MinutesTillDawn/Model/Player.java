package com.MinutesTillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    private Texture playerTexture;
    private Sprite playerSprite;
    public boolean isGuest;
    private User user;
    private int pointsInThisGame = 0;
    private float speed = 5, health =100;
    private float posX = 0, posY = 0;
    private CollisionRect rect;
    private float time;
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public Player(User user, boolean isGuest) {
        this.user = user;
        this.isGuest = isGuest;
        playerTexture = new Texture("characters/" + user.getCharacterName() +"/idle1.png");
        playerSprite = new Sprite(playerTexture);
        float screenCenterX = Gdx.graphics.getWidth() / 2f;
        float screenCenterY = Gdx.graphics.getHeight() / 2f;
        this.posX = screenCenterX;
        this.posY = screenCenterY;
        playerSprite.setPosition(screenCenterX, screenCenterY);
        playerSprite.setSize(playerTexture.getWidth()*5, playerTexture.getHeight()*5);
        rect = new CollisionRect(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, playerTexture.getWidth()*2, playerTexture.getHeight()*2);
    }

    public String getAvatarPath() {
        return user.getAvatarPath();
    }

    public String getCharacterName() {
        return user.getCharacterName();
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

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }
}
