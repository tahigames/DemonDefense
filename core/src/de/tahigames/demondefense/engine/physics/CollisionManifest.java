package de.tahigames.demondefense.engine.physics;

/**
 * Created by Mirco on 15.04.2015.
 */
public class CollisionManifest {

    private PhysicsComponent comp1;
    private PhysicsComponent comp2;

    public CollisionManifest(PhysicsComponent comp1, PhysicsComponent comp2){
        this.comp1 = comp1;
        this.comp2 = comp2;
    }

    public void resolve(){
        comp1.onCollisionWith(comp2.getParent());
        comp2.onCollisionWith(comp1.getParent());
    }

}
