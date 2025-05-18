package com.MinutesTillDawn.Model;

public class CollisionRect {
    float x, y;
    float width, height;
    public CollisionRect(float x, float y, float width, float height) {
        this.x = x;
        this.y= y;
        this.height = height;
        this.width = width;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public boolean collidesWith(CollisionRect rect) {
        return x < rect.x + rect.width && y <rect.y + rect.height && x + width > rect.x && y + rect.height > rect.x;
    }
}
