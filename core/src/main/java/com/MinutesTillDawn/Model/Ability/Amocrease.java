package com.MinutesTillDawn.Model.Ability;

import com.MinutesTillDawn.Model.Player;
import com.MinutesTillDawn.Model.Weapon;

public class Amocrease extends Ability{
    @Override
    public void activate(Player player) {
        Weapon weapon = player.getWeapon();
        weapon.setAmmoMax(weapon.getAmmoMax() + 5);
    }

    @Override
    public void update(Player player, float v) {

    }
}
