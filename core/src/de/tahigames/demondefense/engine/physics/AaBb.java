package de.tahigames.demondefense.engine.physics;

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
        float xDist = maxExtent.x - minExtent.x;
        float yDist = maxExtent.y - minExtent.y;

        minExtent.set(position);
        maxExtent.set(position);
        maxExtent.add(xDist,yDist);
    }

    public Vector2 getMinExtent() {
        return minExtent;
    }

    public Vector2 getMaxExtent() {
        return maxExtent;
    }
}
