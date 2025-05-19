package com.MinutesTillDawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class User {
    private String username;
    private String password;
    private String characterName;
    private String securityQuestion, securityAnswer;
    private int totalPoints = 0;

    public User() {}
    public User(String username, String password, String avatar, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.characterName = avatar;
        this.securityQuestion= securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public String getAvatarPath() {
        return "characters/" + characterName + "/avatar.png";
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getUsername () {
        return  username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void adjustTotalPoints(int amount) {
        totalPoints += amount;
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
