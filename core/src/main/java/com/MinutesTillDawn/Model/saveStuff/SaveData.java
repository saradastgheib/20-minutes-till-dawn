package com.MinutesTillDawn.Model.saveStuff;

import com.badlogic.gdx.utils.Array;

public class SaveData {
    public String username;
    public float playerX, playerY;
    public int playerHP;
    public int kills;
    public float timeRemaining;
    public Array<EnemyData> enemies; // موقعیت دشمن‌ها
    public Array<String> abilities; // لیست توانایی‌ها یا وضعیت‌ها
}
