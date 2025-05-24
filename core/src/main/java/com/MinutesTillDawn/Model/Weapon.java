package com.MinutesTillDawn.Model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    String name;
    private Texture weaponTexture;
    private Sprite weaponSprite;
    private int ammo = 30, ammoMax = 30, damage = 20, projectile = 1,reloadTime = 1; // TODO SET THIS!!!

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

    public int getProjectile() {
        return projectile;
    }

    public void setProjectile(int projectile) {
        this.projectile = projectile;
    }

    public int getAmmoMax() {
        return ammoMax;
    }

    public void setAmmoMax(int ammoMax) {
        this.ammoMax = ammoMax;
    }
}
