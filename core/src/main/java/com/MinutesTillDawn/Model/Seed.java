package com.MinutesTillDawn.Model;

import com.MinutesTillDawn.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Seed {
    private float x, y;
    private Sprite sprite;
    private boolean collected = false;

    public Seed(float x, float y, Texture texture) {
        this.x = x;
        this.y = y;
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        sprite.setSize(texture.getWidth() * 2, texture.getHeight()*2);
    }

    public void update() {

    }

    public void render() {
        if (!collected) {
            sprite.draw(Main.getBatch());
        }
    }

    public boolean checkCollisionWithPlayer(Player player) {
        return sprite.getBoundingRectangle().overlaps(player.getPlayerSprite().getBoundingRectangle());
    }

    public void collect() {
        collected = true;
    }

    public boolean isCollected() {
        return collected;
    }
}
