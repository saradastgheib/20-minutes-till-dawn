package com.MinutesTillDawn.Model;

import com.MinutesTillDawn.Model.Ability.Ability;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Player {
    private Texture playerTexture;
    private Sprite playerSprite;
    public boolean isGuest;
    private User user;
    private int healthPoints = 5; //TODO initialize this
    private float speed = 5;
    private float posX = 0, posY = 0;
    private CollisionRect rect;
    private float time;
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;
    public String selectedHero;
    public String selectedWeapon = "revolver";
    private List<Ability> retrievedAbilities = new ArrayList<>();
    private Weapon weapon ;
    private float damageMultiplier = 1, speedMultiplier = 1;
    public int kills = 0;


    private int xp = 0, level = 1;

    public Player(User user, boolean isGuest) {
        this.user = user;
        this.isGuest = isGuest;
        setSelectedHero(user.getCharacterName());

        rect = new CollisionRect(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, playerTexture.getWidth()*2, playerTexture.getHeight()*2);
    }

    public void setSelectedWeapon(String weaponName) {
        this.selectedWeapon = weaponName;

    }
    public void setSelectedHero(String heroName) {
        selectedHero = heroName;
        playerTexture = new Texture("characters/" + heroName +"/idle1.png");
        playerSprite = new Sprite(playerTexture);
        float screenCenterX = Gdx.graphics.getWidth() / 2f;
        float screenCenterY = Gdx.graphics.getHeight() / 2f;
        this.posX = screenCenterX;
        this.posY = screenCenterY;
        playerSprite.setPosition(screenCenterX, screenCenterY);
        playerSprite.setSize(playerTexture.getWidth()*4f, playerTexture.getHeight()*4f);
    }
    public String getAvatarPath() {
        return user.getAvatarPath();
    }

    public String getCharacterName() {
        return selectedHero;
    }
    public String getUsername () {
        return  user.getUsername();
    }

    public int getTotalPoints() {
        return user.getTotalPoints();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public void addXP(int xp) {
        this.xp += xp;

        while (this.xp >= getXpNeeded()) {
            this.xp -= getXpNeeded();
            upgradeLevel();
        }
    }

    public  void upgradeLevel() {
        level ++;
    }
    public int getXpNeeded(){
        return  level * 20;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
    public void adjustHP(int amount) {
        healthPoints += amount;
    }

    public List<Ability> getRetrievedAbilities() {
        return retrievedAbilities;
    }

    public User getUser() {
        return user;
    }

    public void increaseHP(int amount) {
        healthPoints += amount;
    }
     public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
     }

     public Weapon getWeapon() {
        return weapon;
     }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public void setSpeedMultiplier(float speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }
    public void addAbility(Ability ability) {
        retrievedAbilities.add(ability);
        ability.activate(this);
    }

    public void updateAbilities(float delta) {
        for (Ability ability : retrievedAbilities) {
            ability.update(this, delta);
        }
    }
    public Vector2 getCenter() {
        return new Vector2(
            getPosX() + getPlayerSprite().getWidth() / 2f,
            getPosY() + getPlayerSprite().getHeight() / 2f
        );
    }

    public void setPosition(float x, float y) {
        posX = x;
        posY = y;
    }

    public void setHealth(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setKills(int kills){
        this.kills = kills;
    }
}
