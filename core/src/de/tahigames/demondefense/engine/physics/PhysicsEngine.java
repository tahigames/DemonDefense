package de.tahigames.demondefense.engine.physics;

import java.util.List;

import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Engine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class PhysicsEngine extends Engine<PhysicsComponent> {

    private float simulationTime;
    private float timeToSimulate;

    public PhysicsEngine(int simulationsPerSecond){
        simulationTime = 1f / simulationsPerSecond;
    }

    public void simulate(float delta){
        timeToSimulate += delta;
        while(timeToSimulate > 0){
            List<PhysicsComponent> components = getComponents();

            for (PhysicsComponent c : components){
                c.integrate(delta);
            }

            for (int i = 0; i < components.size() - 1; i++) {
                for (int j = i + 1; j < components.size(); j++) {

                }
            }

            for (PhysicsComponent c : components){
                c.apply();
            }

            timeToSimulate -= simulationTime;
        }
    }

}
