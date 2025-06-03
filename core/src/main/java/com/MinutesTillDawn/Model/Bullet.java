package com.MinutesTillDawn.Model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
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
    public Circle getBoundingCircle () {
        return new Circle(position.x, position.y, radius);
    }
}

