package de.tahigames.demondefense.engine.core.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import de.tahigames.demondefense.engine.core.Core;
import de.tahigames.demondefense.engine.core.Component;
import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.core.rendering.ShapeRenderComponent;

/**
 * Created by Mirco on 14.04.2015.
 */
public abstract class PhysicsComponent extends Component {

    private Bounding bounding;

    private Vector2 position;
    private Vector2 velocity;

    private Vector2 movement;

    private ArrayList<PhysicsComponent> currentCollisions;

    private ShapeRenderComponent debugComponent;

    public PhysicsComponent(Bounding bounding){
        this.bounding = bounding;
        position = new Vector2();
        velocity = new Vector2();

        movement = new Vector2();

        currentCollisions = new ArrayList<>();
    }

    public void integrate(float delta){
        position.set(getParent().getTransformedX(), getParent().getTransformedY());
        movement.set(velocity.x * delta, velocity.y * delta);
        position.add(movement);
        bounding.moveTo(position);
    }

    public void apply() {
        getParent().getPosition().add(movement);
    }

    public CollisionManifest testCollisionWith(PhysicsComponent other){
        return new CollisionManifest(this, other, Collider.areColliding(bounding, other.bounding));
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

    public void enableDebugging(){
        if(debugComponent == null){
            debugComponent = new ShapeRenderComponent(RenderComponent.Realm.Game, RenderComponent.Layer.Zero, bounding, ShapeRenderer.ShapeType.Line, Color.WHITE);
            if(isConnectedToCore())
                getParent().addComponent(debugComponent);
        }
    }

    public void disableDebugging() {
        if(debugComponent != null)
            getParent().removeComponent(debugComponent);
        debugComponent = null;
    }

    @Override
    public void onAddToCore(Core core) {
        super.onAddToCore(core);
        if(debugComponent != null)
            getParent().addComponent(debugComponent);
        core.getPhysicsEngine().addComponent(this);
    }

    @Override
    public void onRemoveFromCore(Core core) {
        super.onRemoveFromCore(core);
        if(debugComponent != null)
            getParent().removeComponent(debugComponent);
        core.getPhysicsEngine().removeComponent(this);
    }
}
