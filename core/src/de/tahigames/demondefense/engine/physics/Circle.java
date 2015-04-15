package de.tahigames.demondefense.engine.physics;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mirco on 15.04.2015.
 */
public class Circle extends Bounding {

    private Vector2 center;
    private float radius;

    public Circle(float centerX, float centerY, float radius) {
        this.center = new Vector2(centerX, centerY);
        this.radius = radius;
    }

    @Override
    public void moveTo(Vector2 position) {
        center.set(position);
    }

    public Vector2 getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }
}
