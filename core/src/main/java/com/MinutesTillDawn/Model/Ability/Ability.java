package com.MinutesTillDawn.Model.Ability;

import com.MinutesTillDawn.Model.Player;

public abstract class Ability {
    protected boolean isActive = false;

    public abstract void activate(Player player);
    public abstract void update(Player player, float v);
    public boolean isActive() {
        return isActive;
    }
}
