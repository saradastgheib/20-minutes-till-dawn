package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.badlogic.gdx.graphics.Texture;
import org.w3c.dom.Text;

public class WorldController {
    private PlayerController playerController;
    private Texture backgroundTexture;
    private float bgX = 0, bgY = 0;

    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture("background.png");
        this.playerController = playerController;
    }

    public void update() {
        bgX = playerController.getPlayer().getPosX();
        bgY = playerController.getPlayer().getPosY();
        Main.getBatch().draw(backgroundTexture, bgX, bgY);
    }
}
