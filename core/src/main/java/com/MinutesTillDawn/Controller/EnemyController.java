package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.Enemy;
import com.MinutesTillDawn.Model.Enums.EnemyType;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyController {

    private float tentacleTimer = 0f;
    GameController controller;
    EnemyController(GameController controller) {
        this.controller = controller;
    }

    public void update(float v) {
        spawnTentacleEnemyIfNeeded(v);
        for (Enemy enemy : controller.enemies) {
            Animation<TextureRegion> animation = GameAssetManager.getGameAssetManager().getEnemyAnimation(enemy.getType());
            enemy.region = animation.getKeyFrame(getGameTime());
        }
    }
    public void render() {

        for (Enemy enemy : controller.enemies) {
            enemy.getEnemySprite().setRegion(enemy.region);
            enemy.getEnemySprite().draw(Main.getBatch());
        }

    }


    public void spawnTentacleEnemyIfNeeded(float delta) {
        tentacleTimer += delta;

        if (tentacleTimer >= 3f) {
            int count = (int) (getGameTime()/30);
            for (int i = 0 ; i < count; i++) {
                controller.spawnEnemy(EnemyType.TENTACLE_MONSTER);
            }
            tentacleTimer = 0f;
        }
    }


    public float getGameTime() {
        return GameSettings.gameTime*60f - controller.timeRemaining;
    }
}
