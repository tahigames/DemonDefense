package de.tahigames.demondefense.engine.physics;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mirco on 15.04.2015.
 */
public class Collider {

    public static boolean areColliding(Bounding b1, Bounding b2){
        if(b1 instanceof Circle && b2 instanceof Circle)
            return circleAndCircle((Circle)b1, (Circle) b2);
        if(b1 instanceof  Circle && b2 instanceof  AaBb)
            return circleAndAaBb((Circle)b1, (AaBb) b2);
        if(b1 instanceof  AaBb && b2 instanceof  Circle)
            return circleAndAaBb((Circle)b2, (AaBb) b1);
        if(b1 instanceof AaBb && b2 instanceof  AaBb)
            return aaBbAndAaBb((AaBb) b1, (AaBb) b2);
        throw new IllegalArgumentException("The Bounding types are not supported yet!");
    }

    private static boolean circleAndAaBb(Circle c, AaBb a){
        Vector2 point = new Vector2();

        float height = a.getMaxExtent().y - a.getMinExtent().y;
        float width = a.getMaxExtent().x - a.getMinExtent().x;

        //bot left
        point.set(a.getMinExtent());
        if(insideCircle(c, point))
            return true;

        //top right
        point.set(a.getMaxExtent());
        if(insideCircle(c, point))
            return true;

        //top left
        point.set(a.getMinExtent()).add(0, height);
        if(insideCircle(c, point))
            return true;

        //bot right
        point.set(a.getMinExtent()).add(width, 0);
        if(insideCircle(c, point))
            return true;

        //center
        point.set(a.getMinExtent().add(a.getMaxExtent())).scl(0.5f);
        if(insideCircle(c, point))
            return true;
        return false;
    }

    private static boolean insideCircle(Circle c, Vector2 point){
        if(Vector2.dst(c.getCenter().x, c.getCenter().y, point.x, point.y) < c.getRadius())
            return true;
        return false;
    }

    private static boolean aaBbAndAaBb(AaBb a1, AaBb a2){
        if(a1.getMaxExtent().x < a2.getMinExtent().x || a1.getMinExtent().x > a2.getMaxExtent().x) return false;
        if(a1.getMaxExtent().y < a2.getMinExtent().y || a1.getMinExtent().y > a2.getMaxExtent().y) return false;

        return true;
    }

    private static boolean circleAndCircle(Circle c1, Circle c2){
        float xDist = c1.getCenter().x - c2.getCenter().x;
        float yDist = c1.getCenter().y - c2.getCenter().y;
        float dist = Vector2.len(xDist, yDist);
        if(dist < (c1.getRadius() + c2.getRadius()))
            return true;
        return false;
    }
}
