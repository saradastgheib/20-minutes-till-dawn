package com.MinutesTillDawn.Controller;

public class WeaponController {
    private WeaponController weaponController;


    public WeaponController getWeaponController() {
        if (weaponController == null) weaponController = new WeaponController();
        return weaponController;
    }
}
