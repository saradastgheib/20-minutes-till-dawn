package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.*;
import com.MinutesTillDawn.Model.Enums.EnemyType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyController {

    private float tentacleTimer = 0f;
    private float eyebatTimer = 0f;
    private float shootTimer = 0f;
    GameController controller;
    List<Seed> seeds = new ArrayList<>();
    public boolean frozen = false;
    EnemyController(GameController controller) {
        this.controller = controller;
    }

    public void update(float v) {
        spawnTentacleEnemyIfNeeded(v);
        spawnEyeBat(v);
        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy enemy : controller.enemies) {
            Random random = new Random();
           // if (random.nextInt() % 10 == 3) enemy.state = EnemyState.DYING;
            if (enemy.state == EnemyState.DYING) {
                enemy.deathTime += v;
                enemy.region = enemy.deathAnimation.getKeyFrame(getGameTime());
                if (enemy.deathAnimation.isAnimationFinished(enemy.deathTime)) {
                    controller.getPlayerController().player.kills ++;
                   enemy.state = EnemyState.DEAD;
                   enemiesToRemove.add(enemy);
                   String path = "monsters/" + enemy.getType() + "/seed.png";
                    seeds.add(new Seed(enemy.getX(), enemy.getY(), new Texture(Gdx.files.internal(path))));
                }
            }
            else {
                Animation<TextureRegion> animation = GameAssetManager.getGameAssetManager().getEnemyAnimation(enemy.getType());
                enemy.region = animation.getKeyFrame(getGameTime());
            }
            if (!frozen) enemy.update(v, controller);
        }
        for (Enemy enemy : enemiesToRemove) {
            controller.enemies.remove(enemy);
        }
        for (Seed seed : seeds) {
            if (!seed.isCollected() && seed.checkCollisionWithPlayer(controller.getPlayerController().player)) {
                seed.collect();
                controller.getPlayerController().player.addXP(3, controller.getView());
            }
        }

    }
    public void render() {

        for (Enemy enemy : controller.enemies) {
            enemy.getEnemySprite().setRegion(enemy.region);
            enemy.getEnemySprite().draw(Main.getBatch());
            if (enemy.checkCollisionWithPlayer(controller.getPlayerController().player) && !controller.getPlayerController().player.isInvincible) {
                if (enemy.type!= EnemyType.TREE){
                    controller.getPlayerController().player.adjustHP(-1);
                    controller.makeInvincible();
                }
            }
        }
        for (Seed seed : seeds) {
            seed.render();
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


    public void spawnEyeBat(float delta) {
        eyebatTimer += delta;
        float t = GameSettings.gameTime * 60f;
        float i = getGameTime();
        if (i >= t/4  && eyebatTimer >=10f) {
            int count = (int) ((4*i-t+30)/30);
            for (int j = 0; j < count; j++) {
                controller.spawnEnemy(EnemyType.EYEBAT);
            }
            eyebatTimer = 0f;
        }
    }
    public float getGameTime() {
        return GameSettings.gameTime*60f - controller.timeRemaining;
    }
}
