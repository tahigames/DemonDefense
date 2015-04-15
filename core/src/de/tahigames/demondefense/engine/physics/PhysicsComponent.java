package de.tahigames.demondefense.engine.physics;

import com.badlogic.gdx.math.Vector2;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Entity;

/**
 * Created by Mirco on 14.04.2015.
 */
public abstract class PhysicsComponent extends Component {


    private Vector2 velocity;

    public void integrate(float delta){

    }

    public abstract void onCollisionWith(Entity e);

    @Override
    public void onAddToCore(Core core) {
        core.getPhysicsEngine().addComponent(this);
    }

    @Override
    public void onRemoveFromCore(Core core) {
        core.getPhysicsEngine().removeComponent(this);
    }
}
