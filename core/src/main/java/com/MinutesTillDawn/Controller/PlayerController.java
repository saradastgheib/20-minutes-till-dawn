package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class PlayerController {
    Player player;
    public void handlePlayerInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setPosY(player.getPosY() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setPosX(player.getPosX() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setPosX(player.getPosX() + player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setPosY(player.getPosY() + player.getSpeed());
        }
    }

    public void idleAnimation() {
        //Animation<Texture> animation = GameAssetManager.getGameAssetManager(
    }

    public Player getPlayer() {
        return player;
    }
}
