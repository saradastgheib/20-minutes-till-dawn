package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.Enemy;
import com.MinutesTillDawn.Model.Enums.EnemyType;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.Text;

public class WorldController {
    private final PlayerController playerController;
    private float bgX = 0, bgY = 0;
    private TextureRegion[][] backgroundTiles;
    private final int tileSize = 32;
    private int tilesX, tilesY;
    private OrthographicCamera camera;

    public WorldController(PlayerController playerController, GameController controller) {
        this.playerController = playerController;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(playerController.getPlayer().getPosX(), playerController.getPlayer().getPosY(), 0);
        camera.update();

        int screenWidth = Gdx.graphics.getWidth() * 3;
        int screenHeight = Gdx.graphics.getHeight() * 3;
        tilesX = screenWidth / tileSize + 4;
        tilesY = screenHeight / tileSize + 4;
        backgroundTiles = new TextureRegion[tilesX][tilesY];
        Array<TextureRegion> forestTiles = GameAssetManager.getGameAssetManager().getForestTiles();
        for (int x = 0; x < tilesX; x++) {
            for (int y = 0; y < tilesY; y++) {
                backgroundTiles[x][y] = forestTiles.random(); // assign once
            }
        }

        spawnInitialTreeEnemies(controller);

    }

    public void spawnInitialTreeEnemies(GameController controller) {
        for (int i = 0; i < 50; i++) {
            float x = MathUtils.random(-2000, 2000);
            float y = MathUtils.random(-2000, 2000);
            Enemy treeEnemy = new Enemy(EnemyType.TREE);
            treeEnemy.setPosition(x, y);
            controller.enemies.add(treeEnemy);
        }
    }
    public void update() {
        camera.position.set(playerController.getPlayer().getPosX(), playerController.getPlayer().getPosY(), 0);
        camera.update();
        Main.getBatch().setProjectionMatrix(camera.combined);
        float camX = camera.position.x - camera.viewportWidth / 2;
        float camY = camera.position.y - camera.viewportHeight / 2;

        int startX = (int) (camX / tileSize) - 1;
        int startY = (int) (camY / tileSize) - 1;

        int endX = startX + tilesX;
        int endY = startY + tilesY;

        Array<TextureRegion> forestTiles = GameAssetManager.getGameAssetManager().getForestTiles();

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                int index = Math.abs((x * 928371 + y * 1928371) % forestTiles.size); // pseudo-random seed
                TextureRegion tile = forestTiles.get(index);
                Main.getBatch().draw(tile, x * tileSize, y * tileSize);
            }
        }
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
