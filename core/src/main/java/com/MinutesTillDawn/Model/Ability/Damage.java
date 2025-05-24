package com.MinutesTillDawn.Model.Ability;

import com.MinutesTillDawn.Model.Player;

public class Damage extends Ability{
    private float duration = 10f;
    private float timer = 0f;

    @Override
    public void activate(Player player) {
        if (!isActive()) {
            isActive = true;
            player.setDamageMultiplier(1.25f);
            timer = duration;
        }
    }

    @Override
    public void update(Player player, float v) {
        if (isActive) {
            timer -= v;
            if (timer <= 0) {
                isActive = false;
                player.setDamageMultiplier(1);
            }
        }
    }
}
