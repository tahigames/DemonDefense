package de.tahigames.demondefense.game.towers;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.ai.AIComponent;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;

/**
 * Created by Marcel on 15.04.2015.
 */
public class ProjectileAI extends AIComponent {

    private static final float PROJECTILE_SPEED = 5.0f;
    private PhysicsComponent physicsComponent;
    private Entity target;

    public ProjectileAI(PhysicsComponent physicsComponent, Entity target){
        this.physicsComponent = physicsComponent;
    }

    @Override
    public void think(float delta) {
        float stepX = target.getX() - getParent().getX();
        float stepY = target.getY() - getParent().getY();

        float length = (float) Math.sqrt(stepX * stepX + stepY * stepY);

        stepX = (stepX / length) * PROJECTILE_SPEED;
        stepY = (stepY / length) * PROJECTILE_SPEED;


        physicsComponent.getVelocity().set(stepX, stepY);

    }
}
