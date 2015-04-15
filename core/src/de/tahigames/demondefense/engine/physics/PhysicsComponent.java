package de.tahigames.demondefense.engine.physics;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Entity;

/**
 * Created by Mirco on 14.04.2015.
 */
public abstract class PhysicsComponent extends Component {

    private Bounding bounding;

    private Vector2 position;
    private Vector2 velocity;

    private ArrayList<PhysicsComponent> currentCollisions;

    public PhysicsComponent(Bounding bounding){
        this.bounding = bounding;
        currentCollisions = new ArrayList<>();
    }

    public void integrate(float delta){
        position.set(getParent().getPosition());
        position.add(velocity.x * delta, velocity.y * delta);
        bounding.moveTo(position);
    }

    public void apply() {
        getParent().getPosition().set(position);
    }

    public CollisionManifest testCollisionWith(PhysicsComponent other){
        return new CollisionManifest(this, other, false);
    }

    public abstract boolean canCollideWith(Entity e);

    public void onCollisionWith(Entity e){
    }

    public boolean isCollidingWith(PhysicsComponent other){
        return currentCollisions.contains(other);
    }

    public ArrayList<PhysicsComponent> getCurrentCollisions() {
        return currentCollisions;
    }

    public Bounding getBounding() {
        return bounding;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public void onAddToCore(Core core) {
        core.getPhysicsEngine().addComponent(this);
    }

    @Override
    public void onRemoveFromCore(Core core) {
        core.getPhysicsEngine().removeComponent(this);
    }
}
