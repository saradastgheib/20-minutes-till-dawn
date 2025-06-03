package com.MinutesTillDawn.Model;


import com.MinutesTillDawn.Controller.GameController;
import com.MinutesTillDawn.Model.Enums.WeaponsInfo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    String name;
    private Texture weaponTexture;
    private Sprite weaponSprite;
    private int ammo, ammoMax, damage, projectile;
    private float reloadTime ;
    private float reloadTimer = 0;
    private boolean isReloading  = false;

    public Weapon(String name){

        this.name = name;
        setWeapon();
        weaponTexture = new Texture("weapons/" + name +"/still.png");
        weaponSprite = new Sprite(weaponTexture);
        weaponSprite.setX((float) Gdx.graphics.getWidth() / 2);
        weaponSprite.setY((float) Gdx.graphics.getHeight() / 2);
        weaponSprite.setSize(50,50);
    }

    public void setWeapon() {
        WeaponsInfo info = WeaponsInfo.valueOf(name.toUpperCase());
        ammo = info.getAmmoMax();
        ammoMax = info.getAmmoMax();
        damage = info.getDamage();
        projectile = info.getProjectile();
        reloadTime = info.getReloadTime();
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
                ammo = ammoMax;
            }
        }
    }

    public boolean canShoot() {
        return !isReloading && ammo > projectile;
    }

    public void shoot() {
        ammo -= projectile;
        if (ammo < projectile &&  GameSettings.autoReloadEnabled) {
            reload();
        }
    }
    public boolean isReloading() {
        return isReloading;
    }
}
