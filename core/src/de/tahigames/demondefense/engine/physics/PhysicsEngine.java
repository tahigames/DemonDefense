package de.tahigames.demondefense.engine.physics;

import java.util.ArrayList;
import java.util.List;

import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Engine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class PhysicsEngine extends Engine<PhysicsComponent> {

    private ArrayList<CollisionManifest> manifests;

    private float simulationTime;
    private float timeToSimulate;

    public PhysicsEngine(int simulationsPerSecond){
        simulationTime = 1f / simulationsPerSecond;

        manifests = new ArrayList<>();
    }

    public void simulate(float delta){
        timeToSimulate += delta;
        while(timeToSimulate > 0){
            List<PhysicsComponent> components = getComponents();

            //place components a step ahead in time
            for (PhysicsComponent c : components){
                c.integrate(delta);
                c.getCurrentCollisions().clear();
            }

            //check for collisions and gather manifests
            manifests.clear();
            for (int i = 0; i < components.size() - 1; i++) {
                PhysicsComponent comp1 = components.get(i);
                for (int j = i + 1; j < components.size(); j++) {
                    PhysicsComponent comp2 = components.get(j);

                    if(comp1.canCollideWith(comp2.getParent()) ||
                       comp2.canCollideWith(comp1.getParent())){
                        CollisionManifest m = comp1.testCollisionWith(comp2);
                        manifests.add(m);
                    }
                }
            }

            //resolve collisions
            for (CollisionManifest m : manifests){
                m.resolve();
            }

            //apply the changes in the physics component to their entity
            for (PhysicsComponent c : components){
                c.apply();
            }

            timeToSimulate -= simulationTime;
        }
    }

}
