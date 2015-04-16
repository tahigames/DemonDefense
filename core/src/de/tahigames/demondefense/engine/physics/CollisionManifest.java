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
            return;
        }

        if(comp1.canCollideWith(comp2.getParent())){
            comp1.getCurrentCollisions().add(comp2);
            comp1.onCollisionWith(comp2.getParent());
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
