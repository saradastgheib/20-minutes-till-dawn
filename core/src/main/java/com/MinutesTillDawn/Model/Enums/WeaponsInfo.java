package com.MinutesTillDawn.Model.Enums;

public enum WeaponsInfo {
    REVOLVER(20, 1, 1, 6),
    SHOTGUN(10, 2, 1, 4),
    SMGSDUAL(8, 1, 2, 24);
    final int damage;
    final int projectile;
    final int reloadTime;
    final int ammoMax;
    WeaponsInfo(int damage, int projectile, int reloadTime, int ammoMax) {
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
        this.ammoMax= ammoMax;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getAmmoMax() {
        return ammoMax;
    }

    public int getDamage() {
        return damage;
    }

    public int getReloadTime() {
        return reloadTime;
    }
}
