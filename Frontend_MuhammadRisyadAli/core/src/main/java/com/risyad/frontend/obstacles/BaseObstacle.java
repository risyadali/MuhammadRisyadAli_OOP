package com.risyad.frontend.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class BaseObstacle {
    protected Rectangle collider;
    protected boolean inUse;

    public BaseObstacle(float x, float y, float width, float height) {
        this.collider = new Rectangle(x, y, width, height);
        this.inUse = false;
    }

    public abstract void update(float delta);

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(collider.x, collider.y, collider.width, collider.height);
    }

    public boolean isColliding(Rectangle playerCollider) {
        return collider.overlaps(playerCollider);
    }

    public boolean isOffScreenCamera(float cameraLeftEdge) {
        return collider.x + collider.width < cameraLeftEdge;
    }

    public void release() {
        this.inUse = false;
    }

    public boolean isInUse() {
        return inUse;
    }

    public Rectangle getCollider() {
        return collider;
    }
}
