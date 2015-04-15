package de.tahigames.demondefense.engine.physics;

/**
 * Created by Mirco on 15.04.2015.
 */
public class CollisionManifest {

    private PhysicsComponent comp1;
    private PhysicsComponent comp2;

    private boolean colliding;

    public CollisionManifest(PhysicsComponent comp1, PhysicsComponent comp2, boolean colliding){
        this.comp1 = comp1;
        this.comp2 = comp2;
        this.colliding = colliding;
    }

    public void resolve(){
        if(!colliding){
            if(comp1.isCollidingWith(comp2))
                comp1.onStopCollidingWith(comp2.getParent());
            if(comp2.isCollidingWith(comp1))
                comp2.onStopCollidingWith(comp1.getParent());
            return;
        }

        if(comp1.canCollideWith(comp2.getParent())){
            comp1.onCollisionWith(comp2.getParent());

            if(!comp1.isCollidingWith(comp2))
                comp1.onStartCollidingWith(comp2.getParent());
        }

        if(comp2.canCollideWith(comp1.getParent())){
            comp2.onCollisionWith(comp1.getParent());

            if(!comp2.isCollidingWith(comp1))
                comp2.onStartCollidingWith(comp1.getParent());
        }
    }

    public boolean colliding(){
        return colliding;
    }

    public PhysicsComponent getComp1() {
        return comp1;
    }

    public PhysicsComponent getComp2() {
        return comp2;
    }
}
