package de.tahigames.demondefense.engine.core.physics;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mirco on 14.04.2015.
 */
public class AaBb extends  Bounding {

    private Vector2 minExtent;
    private Vector2 maxExtent;

    public AaBb(float minX, float minY, float maxX, float maxY){
        minExtent = new Vector2(minX, minY);
        maxExtent = new Vector2(maxX, maxY);
    }

    @Override
    public void moveTo(Vector2 position) {
        float width = getWidth();
        float height = getHeight();

        minExtent.set(position);
        maxExtent.set(position);
        minExtent.sub(width / 2, height / 2);
        maxExtent.add(width / 2, height / 2);
    }

    public Vector2 getMinExtent() {
        return minExtent;
    }

    public Vector2 getMaxExtent() {
        return maxExtent;
    }


    @Override
    public float getWidth() {
        return maxExtent.x - minExtent.x;
    }

    @Override
    public float getHeight() {
        return maxExtent.y - minExtent.y;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer, float delta) {
        shapeRenderer.rect(minExtent.x, minExtent.y, getWidth(), getHeight());
    }
}
