package de.tahigames.demondefense.engine.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.ShapeRenderComponent;

/**
 * Created by Mirco on 14.04.2015.
 */
public abstract class PhysicsComponent extends Component {

    private Bounding bounding;

    private Vector2 position;
    private Vector2 velocity;

    private ArrayList<PhysicsComponent> currentCollisions;

    private ShapeRenderComponent debugComponent;

    public PhysicsComponent(Bounding bounding){
        this.bounding = bounding;
        position = new Vector2();
        velocity = new Vector2();
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
