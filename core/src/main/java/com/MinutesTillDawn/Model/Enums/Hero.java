package com.MinutesTillDawn.Model.Enums;

public enum Hero {
    SHANA("shana", 4, 4),
    DIAMOND("diamond", 7, 1),
    SCARLET("scarlet", 3, 5),
    LILITH("lilith", 5, 3),
    DASHER("dasher", 2, 10);
    ;
    String name;
    int hp, speed;
    Hero(String name, int hp, int speed) {
        this.name = name;
        this.hp = hp;
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }
    public static Hero fromString(String name) {
        for (Hero H : values()) {
            if (H.name.equalsIgnoreCase(name)) {
                return H;
            }
        }
        return SHANA;
    }
}
