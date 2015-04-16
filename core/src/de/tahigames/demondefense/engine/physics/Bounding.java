package de.tahigames.demondefense.engine.physics;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mirco on 15.04.2015.
 */
public abstract class Bounding {
    public abstract void moveTo(Vector2 position);
    public boolean collidesWith(Bounding other){
        return Collider.areColliding(this, other);
    }
    public abstract float getWidth();
    public abstract float getHeight();
    public abstract void render(ShapeRenderer shapeRenderer, float delta);
}
