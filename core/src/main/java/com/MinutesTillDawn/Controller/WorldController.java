package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.Text;

public class WorldController {
    private PlayerController playerController;
    private float bgX = 0, bgY = 0;
    private Array<TextureRegion> forestTiles;
    private TextureRegion[][] backgroundTiles;
    private final int tileSize = 32;
    private int tilesX, tilesY;

    public WorldController(PlayerController playerController) {
        this.playerController = playerController;
        this.forestTiles = GameAssetManager.getGameAssetManager().getForestTiles();
        int screenWidth = Gdx.graphics.getWidth() * 2;
        int screenHeight = Gdx.graphics.getHeight() * 2;

        tilesX = screenWidth / tileSize + 4;
        tilesY = screenHeight / tileSize + 4;

        backgroundTiles = new TextureRegion[tilesX][tilesY];

        for (int x = 0; x < tilesX; x++) {
            for (int y = 0; y < tilesY; y++) {
                backgroundTiles[x][y] = forestTiles.random(); // assign once
            }
        }
    }

    public void update() {
        float playerX = playerController.getPlayer().getPosX();
        float playerY = playerController.getPlayer().getPosY();

        float startX = playerX - (tilesX / 2f) * tileSize;
        float startY = playerY - (tilesY / 2f) * tileSize;

        for (int x = 0; x < tilesX; x++) {
            for (int y = 0; y < tilesY; y++) {
                TextureRegion tile = backgroundTiles[x][y];
                float drawX = startX + x * tileSize;
                float drawY = startY + y * tileSize;

                Main.getBatch().draw(tile, drawX, drawY);
            }
        }
    }
}
