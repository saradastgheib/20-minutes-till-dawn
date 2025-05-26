package com.MinutesTillDawn.Model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    String name;
    private Texture weaponTexture;
    private Sprite weaponSprite;
    private int ammo = 30, ammoMax = 30, damage = 20, projectile = 1; // TODO SET THIS!!!
    private float reloadTime = 10;
    private float reloadTimer = 0;
    private boolean isReloading  = false;

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

    public void reload() {
        if (!isReloading) {
            isReloading = true;
            reloadTimer = reloadTime;
        }
    }

    public void update(float delta) {
        if (isReloading) {
            reloadTimer -= delta;
            if (reloadTimer <= 0) {
                isReloading = false;
            }
        }
    }

    public boolean canShoot() {
        return !isReloading;
    }

    public boolean isReloading() {
        return isReloading;
    }
}
