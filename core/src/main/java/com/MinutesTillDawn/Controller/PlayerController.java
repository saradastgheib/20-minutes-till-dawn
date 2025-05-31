package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
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

    public void update(float v) {


        if (player.isPlayerIdle()) {
            idleAnimation();
        }
        handlePlayerInput(v);
    }
    public void render() {
        player.getPlayerSprite().setPosition(player.getPosX(), player.getPosY());
        player.getPlayerSprite().setRegion(region);
        player.getPlayerSprite().draw(Main.getBatch());
    }
    public void handlePlayerInput(float v) {
        float delta = Gdx.graphics.getDeltaTime();
        float moveAmount = player.getSpeed() * delta * 3;

        if (GameSettings.controlScheme.equals("ARROWS")) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) player.setPosY(player.getPosY() + moveAmount);
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.setPosX(player.getPosX() + moveAmount);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.setPosX(player.getPosX() - moveAmount);
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.setPosY(player.getPosY() - moveAmount);
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) player.setPosY(player.getPosY() + moveAmount);
            if (Gdx.input.isKeyPressed(Input.Keys.D)) player.setPosX(player.getPosX() + moveAmount);
            if (Gdx.input.isKeyPressed(Input.Keys.A)) player.setPosX(player.getPosX() - moveAmount);
            if (Gdx.input.isKeyPressed(Input.Keys.S)) player.setPosY(player.getPosY() - moveAmount);
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
