package com.MinutesTillDawn.Model.Enums;


public class ScoreEntry {
    public String username;
    public int score;
    public int kills;
    public float survivalTime;

    public ScoreEntry() {} // Required for JSON deserialization

    public ScoreEntry(String username, int score, int kills, float survivalTime) {
        this.username = username;
        this.score = score;
        this.kills = kills;
        this.survivalTime = survivalTime;
    }
}
