package com.risyad.frontend.pools; // Use your actual package name

import com.badlogic.gdx.math.Vector2;
import com.risyad.frontend.obstacles.HomingMissile; // Use your actual package

public class HomingMissilePool extends ObjectPool<HomingMissile> {

    @Override
    protected HomingMissile createObject() {
        return new HomingMissile(new Vector2()); // Pass Vector2 as designed
    }

    @Override
    protected void resetObject(HomingMissile missile) {
        missile.setActive(false);
        missile.setPosition(0, 0);
        missile.setTarget(null);
    }

    public HomingMissile obtain(Vector2 position) {
        HomingMissile missile = super.obtain();
        missile.initialize(position, 0);
        missile.setActive(true);
        return missile;
    }
}
