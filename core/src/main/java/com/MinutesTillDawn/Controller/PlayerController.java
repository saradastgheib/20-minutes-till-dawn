package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerController {
    Player player;
    TextureRegion region;
    public PlayerController(Player player) {
        this.player = player;
    }

    public void update() {


        if (player.isPlayerIdle()) {
            idleAnimation();
        }
        handlePlayerInput();
    }
    public void render() {
        player.getPlayerSprite().setRegion(region);
        player.getPlayerSprite().draw(Main.getBatch());
    }
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

        Animation<TextureRegion> animation = GameAssetManager.getGameAssetManager().getIdleAnimation(player.getCharacterName());

        player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        region = animation.getKeyFrame(player.getTime());


    }

    public Player getPlayer() {
        return player;
    }
}
