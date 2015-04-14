package de.tahigames.demondefense.engine.physics;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Component;

/**
 * Created by Mirco on 14.04.2015.
 */
public class PhysicsComponent extends Component {

    public void integrate(float delta){

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
