package com.risyad.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
    private Texture backgroundTexture;
    private TextureRegion backgroundRegion;
    private float width;
    private float height;
    private float currentCameraX;

    public Background() {
        this.backgroundTexture = new Texture("background.png");
        this.backgroundRegion = new TextureRegion(backgroundTexture);
        this.width = 2688f;
        this.height = 1536f;
        this.currentCameraX = 0f;
    }

    public void update(float cameraX) {
        this.currentCameraX = cameraX;
    }

    public void render(SpriteBatch batch) {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        // Calculate scale to fit height to screen
        float scale = screenHeight / height;
        float scaledWidth = width * scale;
        float scaledHeight = height * scale;

        // Calculate horizontal offset for seamless scrolling
        float offsetX = (currentCameraX * scale) % scaledWidth;

        // Draw two background tiles for seamless looping
        batch.draw(backgroundRegion, -offsetX, 0, scaledWidth, scaledHeight);
        batch.draw(backgroundRegion, -offsetX + scaledWidth, 0, scaledWidth, scaledHeight);

        // Draw third tile if needed to cover screen edges
        if (offsetX > 0) {
            batch.draw(backgroundRegion, -offsetX - scaledWidth, 0, scaledWidth, scaledHeight);
        }
    }

    public void dispose() {
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
        }
    }
}
