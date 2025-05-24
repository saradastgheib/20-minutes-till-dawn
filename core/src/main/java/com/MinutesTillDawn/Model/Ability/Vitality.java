package com.MinutesTillDawn.Model.Ability;

import com.MinutesTillDawn.Model.Player;

public class Vitality extends Ability {

    @Override
    public void activate(Player player) {
        if (!isActive()) {
            player.increaseHP(1);
        }
    }

    @Override
    public void update(Player player, float v) {

    }

}
