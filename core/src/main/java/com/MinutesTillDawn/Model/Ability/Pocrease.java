package com.MinutesTillDawn.Model.Ability;

import com.MinutesTillDawn.Model.Player;
import com.MinutesTillDawn.Model.Weapon;

public class Pocrease extends Ability{
    @Override
    public void activate(Player player) {
        Weapon weapon = player.getWeapon();
        weapon.setProjectile(weapon.getProjectile() + 1);
    }

    @Override
    public void update(Player player, float v) {

    }
    @Override
    public String getName() {
        return "procrease";
    }
}
