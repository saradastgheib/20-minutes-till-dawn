package com.MinutesTillDawn.Model.Ability;

import com.MinutesTillDawn.Model.Player;

public class Speedy extends Ability{
    private float duration = 10f;
    private float timer = 0;
    @Override
    public void activate(Player player) {
        if (!isActive) {
            player.setSpeedMultiplier(2f);
            timer = duration;
             isActive = true;
        }
    }

    @Override
    public void update(Player player, float v) {

        if (isActive) {
            timer -= v;
            if (timer <= 0) {
                isActive = false;
                player.setSpeedMultiplier(1);
            }
        }
    }
}
