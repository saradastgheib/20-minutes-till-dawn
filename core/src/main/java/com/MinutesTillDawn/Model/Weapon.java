package com.MinutesTillDawn.Model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    String name;
    private Texture weaponTexture;
    private Sprite weaponSprite;
    private int ammo = 30;

    public Weapon(String name){

        this.name = name;
        weaponTexture = new Texture("weapons/" + name +"/still.png");
        weaponSprite = new Sprite(weaponTexture);
        weaponSprite.setX((float) Gdx.graphics.getWidth() / 2 );
        weaponSprite.setY((float) Gdx.graphics.getHeight() / 2);
        weaponSprite.setSize(50,50);
    }

    public Sprite getSprite() {
        return weaponSprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }
}
