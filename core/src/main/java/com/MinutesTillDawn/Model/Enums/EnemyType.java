package com.MinutesTillDawn.Model.Enums;

import com.badlogic.gdx.graphics.Texture;

public enum EnemyType {
    TENTACLE_MONSTER(25),
    EYEBAT(50),
    ELDER(400)
    ;
    final int hp;
    EnemyType(int hp){
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public Texture getTexture() {
        return new Texture("monsters/" + this.name().toLowerCase() + "/pic1.png");
    }
}
