package com.risyad.frontend.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.risyad.frontend.Player;

public class HomingMissile extends BaseObstacle {
    private Player target;
    private float speed;
    private Vector2 velocity;
    private static final float DEFAULT_SPEED = 200f;
    private static final float SIZE = 30f;
    private static final float HOMING_STRENGTH = 2f;

    public HomingMissile(float x, float y) {
        super(x, y, SIZE, SIZE);
        this.speed = DEFAULT_SPEED;
        this.velocity = new Vector2(-speed, 0);
        this.inUse = true;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    @Override
    public void update(float delta) {
        if (target != null && !target.isDead()) {
            // Calculate direction to target
            Vector2 targetPosition = target.getPosition();
            float targetX = targetPosition.x + target.getCollider().width / 2;
            float targetY = targetPosition.y + target.getCollider().height / 2;

            float missileCenterX = collider.x + collider.width / 2;
            float missileCenterY = collider.y + collider.height / 2;

            // Calculate angle to target
            float angleToTarget = MathUtils.atan2(targetY - missileCenterY, targetX - missileCenterX);

            // Update velocity to home in on target
            velocity.x = MathUtils.cos(angleToTarget) * speed;
            velocity.y = MathUtils.sin(angleToTarget) * speed;
        }

        // Update position
        collider.x += velocity.x * delta;
        collider.y += velocity.y * delta;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.ORANGE);
        super.render(shapeRenderer);
    }

    @Override
    public void release() {
        super.release();
        this.target = null;
        this.velocity.set(-speed, 0);
    }
}
