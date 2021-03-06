package de.tahigames.demondefense.engine.core.physics;

import java.util.ArrayList;
import java.util.List;

import de.tahigames.demondefense.engine.core.Engine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class PhysicsEngine extends Engine<PhysicsComponent> {

    private boolean debug;

    private ArrayList<CollisionManifest> manifests;

    private float simulationTime;
    private float passedTime;

    public PhysicsEngine(int simulationsPerSecond){
        simulationTime = 1f / simulationsPerSecond;

        manifests = new ArrayList<>();
    }

    public void simulate(float delta){
        passedTime += delta;
        while(passedTime > simulationTime){
            List<PhysicsComponent> components = getComponents();

            //place components a step ahead in time
            for (PhysicsComponent c : components){
                c.integrate(simulationTime);
                c.getCurrentCollisions().clear();
            }

            //check for collisions and gather manifests
            //very bad collision code!
            //TODO dont test everything with everything...
            manifests.clear();
            for (int i = 0; i < components.size(); i++) {
                PhysicsComponent comp1 = components.get(i);
                for (int j = 0; j < components.size(); j++) {
                    PhysicsComponent comp2 = components.get(j);

                    if(comp1.canCollideWith(comp2.getParent()))
                        manifests.add(comp1.testCollisionWith(comp2));
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

            passedTime -= simulationTime;
        }
    }

    public void enableDebug(){
        this.debug = true;
        for(PhysicsComponent c : getComponents())
            c.enableDebugging();
    }

    @Override
    public void addComponent(PhysicsComponent component) {
        super.addComponent(component);
        if(debug)
            component.enableDebugging();
    }
}
