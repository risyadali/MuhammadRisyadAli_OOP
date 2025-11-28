package com.risyad.frontend.observers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreUIObserver implements Observer {
    private BitmapFont font;
    private SpriteBatch batch;

    public ScoreUIObserver() {
        this.font = new BitmapFont();
        this.font.setColor(Color.WHITE);
        this.font.getData().setScale(2f); // Make it more visible
        this.batch = new SpriteBatch();
    }

    @Override
    public void update(int score) {
        System.out.println("Score updated: " + score);
    }

    public void render(int score) {
        batch.begin();
        font.draw(batch, "Score: " + score + "m", 20, Gdx.graphics.getHeight() - 20);
        batch.end();
    }

    public void dispose() {
        if (font != null) {
            font.dispose();
        }
        if (batch != null) {
            batch.dispose();
        }
    }
}
