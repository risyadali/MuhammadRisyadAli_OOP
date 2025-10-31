package com.risyad.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Ground {
    private static final float GROUND_HEIGHT == 50f;
    private Rectangle groundCollisionArea;
    private Rectangle collider;

    public Ground Collider() {
        this.collider = new Rectangle(0,0, Gdx.graphics.getWidth()*2, GROUND_HEIGHT);
        return collider();
    }

    public void update(float cameraX) {
        float groundWidth = Gdx.graphics.getWidth()+1280;
        float posX + cameraX - Gdx.graphics.getWidth() / 2f - 500;
        collider.setPosition(posX,0);
        collider.setWidth(Gdx.graphics.getWidth()*2);

    }
}

