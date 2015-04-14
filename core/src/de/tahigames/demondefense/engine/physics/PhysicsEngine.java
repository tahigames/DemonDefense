package de.tahigames.demondefense.engine.physics;

import de.tahigames.demondefense.engine.Engine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class PhysicsEngine extends Engine<PhysicsComponent> {

    public void simulate(float delta){
        for (PhysicsComponent c : getComponents()){
            c.integrate(delta);
        }
    }

}
