package com.MinutesTillDawn.Model;

import com.MinutesTillDawn.Controller.GameController;
import com.MinutesTillDawn.Model.Enums.EnemyType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;




public class Enemy {
    public EnemyState state = EnemyState.ALIVE;
    private Texture enemyTexture;
    private final Sprite enemySprite;
    public TextureRegion region;
    private float posX = 950, posY = 500;
    private int hp;
    private final EnemyType type;
    public float deathTime = 0f;
    public Animation<TextureRegion> deathAnimation;

    public Enemy(EnemyType type) {
        enemyTexture = type.getTexture();
        enemySprite = new Sprite(enemyTexture);
        if (type != EnemyType.EYEBAT)
            enemySprite.setSize(enemyTexture.getWidth()*3, enemyTexture.getHeight() * 3);
        hp = type.getHp();
        this.type = type;
        deathAnimation = GameAssetManager.getGameAssetManager().getDeathAnimation();
    }
    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public Vector2 getPosition() {
        return new Vector2(posX + enemySprite.getWidth()/2, posY + enemySprite.getHeight()/2);
    }

    public int getHealth() {
        return  hp;
    }

    public String getType() {
        return type.name().toLowerCase();
    }

    public void setPosition(float x, float y) {
        posX = x;
        posY = y;
        enemySprite.setPosition(posX, posY);
    }
    public void setHealth(int health) {
        hp = health;
    }

    public Sprite getEnemySprite() {
        return enemySprite;
    }

    public void die() {
        state = EnemyState.DYING;
        deathTime = 0f;

    }

    public boolean checkCollisionWithPlayer(Player player) {
        return enemySprite.getBoundingRectangle().overlaps(player.getPlayerSprite().getBoundingRectangle());
    }
    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            die();
        }
    }

    public void update(float v, GameController controller) {
        if (type!=EnemyType.TREE){
            Player player = controller.getPlayerController().getPlayer();
            Vector2 target = new Vector2(player.getPosX(), player.getPosY());
            Vector2 direction = target.sub(getX(), getY()).nor();
            float speed = 25;
            setPosition(getX() + direction.x * speed * v, getY() + direction.y * speed * v);
        }

    }

}
