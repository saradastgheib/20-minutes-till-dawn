package com.MinutesTillDawn.Model;//package com.MinutesTillDawn.Model;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//
//public class Bullet {
//    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
//    private Sprite sprite = new Sprite(texture);
//    private int damage = 5;
//    private int x;
//    private int y;
//
//    public Bullet(int x, int y){
//        sprite.setSize(20 , 20);
//        this.x = x;
//        this.y = y;
//        sprite.setX((float) Gdx.graphics.getWidth() / 2);
//        sprite.setY((float) Gdx.graphics.getHeight() / 2);
//    }
//
//    public Texture getTexture() {
//        return texture;
//    }
//
//    public Sprite getSprite() {
//        return sprite;
//    }
//
//    public int getDamage() {
//        return damage;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//}

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    public Vector2 position;
    public Vector2 velocity;
    public float speed = 500f;
    public float radius = 5f;

    public Bullet(Vector2 start, Vector2 target) {
        this.position = new Vector2(start);
        this.velocity = target.sub(start).nor().scl(speed); // Normalize and scale
    }

    public void update(float delta) {
        position.mulAdd(velocity, delta); // pos += velocity * delta
    }

    public void draw(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        renderer.circle(position.x, position.y, radius);
    }
}

